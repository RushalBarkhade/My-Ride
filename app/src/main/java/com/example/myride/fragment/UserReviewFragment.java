package com.example.myride.fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.R;
import com.example.myride.firebase.UserReview;
import com.example.myride.recycle_views.ReviewRecycleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserReviewFragment extends AbstractFragment {

    public UserReviewFragment(){
    }

    @Override
    public void onStart() {
        super.onStart();
        final View view = getView();
        setLayout(view);
    }

    void setLayout(final View view) {
        activity.getFirebase().getRiderList().orderByKey().equalTo(activity.getUserKey()).
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()>0) {
                    activity.service.execute(new Runnable() {
                        @Override
                        public void run() {
                            final List<UserReview> rideDetails = new ArrayList<>();
                            for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                                UserReview review = snapshot.getValue(UserReview.class);
                                rideDetails.add(review);
                                Log.v("TAG",String.valueOf(rideDetails.size()));
                            }
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    ReviewRecycleAdapter recycle = new ReviewRecycleAdapter(rideDetails);
                                    RecyclerView recycleView = setRecycleView(view);
                                    recycleView.setAdapter(recycle);
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
                }
                else
                    setEmptyTextView(view);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.ride_fragment;
    }
}
