package com.example.myride;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.myride.firebase.RideDetails;
import com.example.myride.firebase.RideTime;
import com.example.myride.firebase.Ridefacility;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Future;

public class Main2Activity extends AlterFunctionActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView sourceTime;
    private TextView destinationTime;

    private Map<Integer,Boolean> saparseCheck = new TreeMap<>();

    private String sourceName;
    private String destinationName;

    private RideTime rideSourceTime = new RideTime();
    private RideTime rideDestinationTime = new RideTime();
    private Future<?> intentFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Runnable intentDataLoader = new Runnable() {
            @Override
            public void run() {
                setDefaultDate();
            }
        };
        intentFuture = service.submit(intentDataLoader);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sourceTime =findViewById(R.id.source_time);
        destinationTime =findViewById(R.id.destination_time);
        final RadioGroup rideTypeSelect = findViewById(R.id.groupradio);
        rideTypeSelect.clearCheck();

        sourceTime.setOnClickListener(this);
        destinationTime.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int selectedID = rideTypeSelect.getCheckedRadioButtonId();
                     if(selectedID==-1)
                         Toast.makeText(getApplicationContext(), "No answer has been selected", Toast.LENGTH_SHORT).show();
                      else {
                         RadioButton radioButton = rideTypeSelect.findViewById(selectedID);
                         String rideType = radioButton.getText().toString().toLowerCase();
                         saveDataInFirebase(rideType);
                     }
            }
        });

        MaterialCheckBox tvCheckBox = findViewById(R.id.tv_id);
        MaterialCheckBox chargingCheckBox = findViewById(R.id.charging_id);
        MaterialCheckBox aCCheckBox = findViewById(R.id.ac_id);
        MaterialCheckBox wifiCheckBox = findViewById(R.id.wifi_id);
        MaterialCheckBox headsetCheckBox = findViewById(R.id.headset_id);
        MaterialCheckBox readingCheckBox = findViewById(R.id.reading_light_id);
        MaterialCheckBox blanketCheckBox = findViewById(R.id.blanket_id);
        MaterialCheckBox newspaperCheckBox = findViewById(R.id.newspaper_id);

        tvCheckBox.setOnCheckedChangeListener(this);
        chargingCheckBox.setOnCheckedChangeListener(this);
        aCCheckBox.setOnCheckedChangeListener(this);
        wifiCheckBox.setOnCheckedChangeListener(this);
        headsetCheckBox.setOnCheckedChangeListener(this);
        readingCheckBox.setOnCheckedChangeListener(this);
        blanketCheckBox.setOnCheckedChangeListener(this);
        newspaperCheckBox.setOnCheckedChangeListener(this);

//       getRidesList().orderByChild("destinationDate/day").equalTo(23).addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                   RideDetails details = dataSnapshot1.getValue(RideDetails.class);
//                   Log.v("TAG", details.getSourceName());
//                   Log.v("TAG", details.getDestinationName());
//                   Log.v("TAG", String.valueOf(details.getCost()));
//               }
//           }
//           @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//           }
//       });
    }
    private void saveDataInFirebase(final String rideType){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Ridefacility ridefacility = new Ridefacility();
                for(Integer key:saparseCheck.keySet()){
                    boolean value = saparseCheck.get(key);
                    if (R.id.charging_id==key)
                        ridefacility.setChargingPoint(value);
                    if (R.id.ac_id==key)
                        ridefacility.setAc(value);
                    if (R.id.wifi_id==key)
                        ridefacility.setWifi(value);
                    if (R.id.tv_id==key)
                        ridefacility.setTv(value);
                    if(R.id.headset_id==key)
                        ridefacility.setHeadset(value);
                    if (R.id.reading_light_id==key)
                        ridefacility.setReadingLight(value);
                    if (R.id.blanket_id==key)
                        ridefacility.setBanket(value);
                    if (R.id.newspaper_id==key)
                        ridefacility.setNewsPaper(value);
                }

                RideDetails details = new RideDetails(rideSourceTime, rideDestinationTime,sourceName, destinationName,300,ridefacility,rideType);
                String keyRide = details.getSourceName()+"_"+details.getDestinationName()+"_"+rideSourceTime.getDay()+"_"+rideSourceTime.getMonth()+"_"+rideSourceTime.getYear();
                if (intentFuture.isDone()) {
                    String key = getFirebase().getRidesKey();
                    getFirebase().getRidesSearchKeyNode().child(key).setValue(keyRide);
                    getFirebase().getRiderList().child(key).setValue(details);
                    Log.v("TAG","INSERTED");
                }
            }
        };
        service.execute(runnable);
    }

    private void setDefaultDate() {
        Intent intent = getIntent();
        Calendar c  = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (intent!=null) {
            year = intent.getIntExtra(YEAR, year);
            month = intent.getIntExtra(MONTH, month);
            day = intent.getIntExtra(DAY, day);
            sourceName = intent.getStringExtra(SOURCE_NAME);
            destinationName = intent.getStringExtra(DESTINATION_NAME);
        }
//        Log.v("TAG",String.valueOf(year));
//        Log.v("TAG",String.valueOf(month));
//        Log.v("TAG",String.valueOf(day));
//        Log.v("TAG",sourceName);
//        Log.v("TAG",destinationName);
        rideSourceTime.setYear(year);
        rideSourceTime.setMonth(month);
        rideSourceTime.setDay(day);
        rideDestinationTime.setYear(year);
        rideDestinationTime.setMonth(month);
        rideDestinationTime.setDay(day);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void getValues(Object values,char flag) {

    }

    @Override
    protected Context getActivity() {
        return this;
    }

    @Override
    protected void setData(int id,int year, int month, int datOfMonth) {
    }

    @Override
    protected void setTime(int id, final int hour, final int min) {
        if (id == R.id.source_time) {
            sourceTime.setText(String.valueOf(hour) + ":" + String.valueOf(min));
            rideSourceTime.setHour(hour);
            rideSourceTime.setMin(min);
        }
        if (id == R.id.destination_time) {
            destinationTime.setText(String.valueOf(hour) + ":" + String.valueOf(min));
            rideDestinationTime.setHour(hour);
            rideDestinationTime.setMin(min);
        }
    }

    @Override
    public void getPlace(String place) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked)
                  saparseCheck.put(buttonView.getId(),true);
              else
                  saparseCheck.remove(buttonView.getId());
        }
}
