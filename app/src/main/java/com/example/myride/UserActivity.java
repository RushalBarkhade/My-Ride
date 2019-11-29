package com.example.myride;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.myride.fragment.UserFragment;
import com.example.myride.fragment.UserPhotosFragment;
import com.example.myride.fragment.UserReviewFragment;
import com.example.myride.utils.ViewPagerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

public class UserActivity extends AbstractActivity {

    private String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar myToolbar =findViewById(R.id.htab_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout ctl = findViewById(R.id.htab_collapse_toolbar);
        //ctl.setTitle("Best Coupons Deals");

        ctl.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title);
        ctl.setExpandedTitleTextAppearance(R.style.exp_toolbar_title);

        TabLayout layout = findViewById(R.id.htab_tabs);
        ViewPager viewPager = findViewById(R.id.htab_viewpager);

        layout.setupWithViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragments(new UserFragment(),"Profile");
        adapter.addFragments(new UserPhotosFragment(),"Photos");
        adapter.addFragments(new UserReviewFragment(),"Reviews");

        viewPager.setAdapter(adapter);

//        layout.getTabAt(0).setIcon(R.drawable.ic_directions_car_black_24dp);
//        layout.getTabAt(1).setIcon(R.drawable.ic_directions_bus_black_24dp);
    }




    @Override
    public int getLayout() {
        return R.layout.activity_user;
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
