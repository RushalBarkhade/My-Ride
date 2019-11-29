package com.example.myride.recycle_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myride.R;

import java.util.List;

public class UserRideAdapter extends RecyclerView.Adapter<UserRideAdapter.UserRideHolder> {

    private List<String> rideImages;
    private Context con;

    public UserRideAdapter(Context context,List<String> rideImages) {
        this.rideImages = rideImages;
        this.con=context;
    }


    @NonNull
    @Override
    public UserRideAdapter.UserRideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.photo_card_layout,parent,false);
        return new UserRideAdapter.UserRideHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRideAdapter.UserRideHolder holder, int position) {
        String uri = rideImages.get(position);
        Glide.with(con).load(uri).into(holder.view);
    }

    @Override
    public int getItemCount() {
        return rideImages.size();
    }

    class UserRideHolder extends RecyclerView.ViewHolder{
        ImageView view;
        UserRideHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.image_of_ride);
        }
    }
}
