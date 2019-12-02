package com.example.myride;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.recycle_views.SearchRecycleView;

public class MainActivity extends AlterFunctionActivity {
    private TextView journeyStart;
    private TextView journeyEnd;
    private TextView journeyDate;

    private String journeyStringDate;
    private String journeyStringStart;
    private String journeyStringEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        journeyStart = findViewById(R.id.journey_start);
        journeyEnd = findViewById(R.id.journey_end);
        journeyDate = findViewById(R.id.journey_date);
        journeyDate.setOnClickListener(this);
        journeyEnd.setOnClickListener(this);
        journeyStart.setOnClickListener(this);

        RecyclerView top_root_recycle_view = findViewById(R.id.top_root_recyclc_view);
        final String[] places = getResources().getStringArray(R.array.top_root);
        SearchRecycleView adapter = new SearchRecycleView(places, false,R.layout.top_card_layout);
        top_root_recycle_view.setHasFixedSize(true);
        top_root_recycle_view.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        top_root_recycle_view.setAdapter(adapter);
        adapter.setClickListener(this);
        final Button want_ride_button = findViewById(R.id.want_ride);
        final Button take_ride_button = findViewById(R.id.ask_ride);
        want_ride_button.setOnClickListener(this);
        take_ride_button.setOnClickListener(this);

        service.execute(new Runnable() {
            @Override
            public void run() {
                LinearLayout linearLayout = findViewById(R.id.linear_layout);
                linearLayout.measure(0,0);
                final int width = linearLayout.getMeasuredWidth();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        want_ride_button.setWidth((width/2)+20);
                        take_ride_button.setWidth((width/2)+20);
                    }
                });
            }
        });





//        final UserEntity entity = new UserEntity("Rushal","9850235926","rushalbarkhade11795@gmail.com");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                db.userDao().insert(entity);
//                List<UserEntity> user = db.userDao().getAllUser();
//                Log.v("TAG",Integer.toString(user.size()));
//            }
//        }).start();
        //Intent intent =new Intent(this,SearchActivity.class);
        //startActivityForResult(intent, SEARCH_COMPLETION_ID);

    }
    private Intent getPackedIntent(String value,Intent intent){
        String[] date = value.split("/");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        intent.putExtra(YEAR,year);
        intent.putExtra(MONTH,month);
        intent.putExtra(DAY,day);
        intent.putExtra(SOURCE_NAME,journeyStringStart);
        intent.putExtra(DESTINATION_NAME,journeyStringEnd);
        return intent;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == journeyDate.getId())
            super.onClick(v);
        if (v.getId()==R.id.ask_ride){
            Intent intent = new Intent(this,Main2Activity.class);
            intent = getPackedIntent(journeyStringDate,intent);
            startActivity(intent);
        }
        if (v.getId()==R.id.want_ride){
            Intent intent = new Intent(this,RideActivity.class);
            intent = getPackedIntent(journeyStringDate,intent);
            startActivity(intent);
        }
        if (v.getId() == journeyStart.getId()) {
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(FLAG, 's');
                startActivityForResult(intent, SEARCH_COMPLETION_ID);
        }
        if (v.getId() == journeyEnd.getId()){
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(FLAG, 'e');
                startActivityForResult(intent, SEARCH_COMPLETION_ID);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bus_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_favorite) {
            Toast.makeText(getApplicationContext(), "ACTION CLICKED", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void getValues(Object name,char flag) {
        String place =(String) name;
        if (flag=='e') {
            journeyEnd.setText(place);
            this.journeyStringStart = place;
        }if (flag=='s') {
            journeyStart.setText(place);
            this.journeyStringEnd = place;
        }
    }

    @Override
    protected Context getActivity() {
        return this;
    }

    @Override
    protected void setData(int id,int year, int month, int dayOfMonth) {
        journeyStringDate = dayOfMonth+"/"+month+"/"+year;
        journeyDate.setText(journeyStringDate);
    }

    @Override
    protected void setTime(int id,int hour, int min) {

    }

    @Override
    public void getPlace(String place) {
        Log.v("TAG",place);
    }
}
