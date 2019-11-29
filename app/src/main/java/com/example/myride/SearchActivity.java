package com.example.myride;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.recycle_views.SearchRecycleView;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchActivity extends AbstractActivity {

    private String placename;
    private  SearchActivity activity = this;
    private char flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        if (intent!=null)
            flag = intent.getCharExtra(FLAG,'n');

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            final TextView view1 = findViewById(R.id.textView1);
            final RecyclerView recyclerView = findViewById(R.id.recent_recycle_view_id);
            final Handler handler = new Handler();
            String apiKey = getString(R.string.api_key);

            if (!Places.isInitialized())
                Places.initialize(getApplicationContext(), apiKey);

            Calendar c = Calendar.getInstance();
            Date date = c.getTime();

            final String[] places = getResources().getStringArray(R.array.top_locations);
            service.execute(new Runnable() {
                @Override
                public void run() {
                    final String[] user = db.recentPlacesDao().getRecentPlaces();
                    if (user.length > 0) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view1.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                SearchRecycleView adapter = new SearchRecycleView(places, false,R.layout.places_recycle_view);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                                recyclerView.setAdapter(adapter);
                                adapter.setClickListener(activity);
                            }
                        });
                    }
                }
            });


            //final RecentPlacesEntity entity = new RecentPlacesEntity("Rushal",date);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                db.recentPlacesDao().insert(entity);
//                List<String> user = db.recentPlacesDao().getRecentPlaces();
//                Log.v("TAG",Integer.toString(user.size()));
//            }
//        }).start();
//
//

            RecyclerView top_location_recycle_view = findViewById(R.id.popular_recycle_view_id);
            SearchRecycleView adapter = new SearchRecycleView(places, false,R.layout.places_recycle_view);
            top_location_recycle_view.setHasFixedSize(true);
            top_location_recycle_view.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            top_location_recycle_view.setAdapter(adapter);
            adapter.setClickListener(this);

            if (placename != null && placename.length() > 0) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT, placename);
                returnIntent.putExtra("FLAG",flag);
                setResult(SearchActivity.RESULT_OK, returnIntent);
                finish();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                onSearchCalled();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSearchCalled() {
        List<Place.Field> fields;
        fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY , fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void getValues(Object values,char flag) {
        this.placename = (String) values;
    }

    @Override
    public void getPlace(String place) {
        this.placename = place;
        Log.v("TAG",place);
        Intent returnIntent = new Intent();
        returnIntent.putExtra(RESULT, placename);
        returnIntent.putExtra(FLAG,flag);
        setResult(SearchActivity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}