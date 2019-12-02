package com.example.myride;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.myride.fragment.BikeRideFragment;
import com.example.myride.fragment.BusRideFragment;
import com.example.myride.fragment.CarRideFragment;
import com.example.myride.utils.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class RideActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabLayout tabLayout = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.view_pager);

        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragments(new CarRideFragment(),"Car");
        adapter.addFragments(new BusRideFragment(),"Bus");
        adapter.addFragments(new BikeRideFragment(),"Bike");

        viewPager.setAdapter(adapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_directions_car_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_directions_bus_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_directions_bike_black_24dp);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_ride;
    }

    @Override
    public void getValues(Object values, char flag) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getPlace(String place) {

    }
}
