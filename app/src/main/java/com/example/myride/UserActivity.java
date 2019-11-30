package com.example.myride;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myride.fragment.UserFragment;
import com.example.myride.fragment.UserPhotosFragment;
import com.example.myride.fragment.UserReviewFragment;
import com.example.myride.utils.ViewPagerAdapter;
import com.example.myride.verification.LicenceVerification;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AbstractActivity {

private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Toolbar toolbar =findViewById(R.id.AppBar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("Collapsing Tool Bar");

        // Set collapsing tool bar image.
        CircleImageView collapsingToolbarImageView = findViewById(R.id.collapsing_toolbar_image_view);
        collapsingToolbarImageView.setImageResource(R.drawable.login_bk);





//        TabLayout layout = findViewById(R.id.htab_tabs);
//        ViewPager viewPager = findViewById(R.id.htab_viewpager);
//
//        layout.setupWithViewPager(viewPager);
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        adapter.addFragments(new UserFragment(),"Profile");
//        adapter.addFragments(new UserPhotosFragment(),"Photos");
//        adapter.addFragments(new UserReviewFragment(),"Reviews");
//        service.execute(new Runnable() {
//           @Override
//           public void run() {
//               LicenceVerification verification= new LicenceVerification("MH1920140017928","20-05-1993");
//
//           }
//       });
//
//        viewPager.setAdapter(adapter);

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
