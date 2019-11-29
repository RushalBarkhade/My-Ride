package com.example.myride.fragment;

import android.view.View;

import com.example.myride.R;

public class UserFragment extends AbstractFragment {


    public UserFragment(){}

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();


    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.user_profile_layout;
    }
}
