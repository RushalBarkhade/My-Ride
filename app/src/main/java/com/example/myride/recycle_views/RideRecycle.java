package com.example.myride.recycle_views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.R;
import com.example.myride.firebase.RideDetails;

import java.util.List;

public class RideRecycle extends RecyclerView.Adapter<RideRecycle.RideRecycleHolder> {

    private List<RideDetails> rideDetails;

    public RideRecycle(List<RideDetails> rideDetails){
        this.rideDetails = rideDetails;
    }

    @NonNull
    @Override
    public RideRecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ride_recycle_view,parent,false);
        return new RideRecycleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RideRecycleHolder holder, int position) {
        RideDetails details = rideDetails.get(position);
        //holder.cost.setText(details.getCost());
        holder.sourceName.setText(details.getSourceName());
        holder.destinationName.setText(details.getDestinationName());
    }

    @Override
    public int getItemCount() {
        return rideDetails.size();
    }

    class RideRecycleHolder extends RecyclerView.ViewHolder{
        private TextView username;
        private TextView rating;
        private TextView sourceName;
        private TextView sourceTime;
        private TextView destinationName;
        private TextView destinationTime;
        private TextView cost;
        private ImageView userImage;

        RideRecycleHolder(@NonNull View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.user_name);
            this.rating = itemView.findViewById(R.id.rating_id);
            this.sourceName = itemView.findViewById(R.id.source_id);
            this.sourceTime = itemView.findViewById(R.id.source_time_id);
            this.destinationName = itemView.findViewById(R.id.destination_id);
            this.destinationTime = itemView.findViewById(R.id.destination_time_id);
            this.cost = itemView.findViewById(R.id.cost);
            this.userImage = itemView.findViewById(R.id.rider_image_id);
        }
    }
}
