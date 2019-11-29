package com.example.myride.recycle_views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.AbstractActivity;
import com.example.myride.R;

public class SearchRecycleView extends RecyclerView.Adapter<SearchRecycleView.SearchPlaceHolder> {

    private String[] places;
    private boolean recent;
    private int layout;

    private ClickListener  clickListener;

    public void setClickListener(AbstractActivity clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void getPlace(String place);
    }

    public SearchRecycleView(String[] places,boolean recent,int layout) {
        this.places = places;
        this.recent = recent;
        this.layout = layout;
    }

    @NonNull
    @Override
    public SearchPlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(layout,parent,false);
        return new SearchPlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPlaceHolder holder, int position) {
        final String name = places[position];
        Log.v("PLACE",name);
        holder.place_name.setText(name);
        if (recent)
            holder.img.setImageResource(R.drawable.ic_location_on_black_24dp);
        else
            holder.img.setImageResource(R.drawable.ic_location_city_black_24dp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.getPlace(name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.length;
    }

    static class SearchPlaceHolder extends RecyclerView.ViewHolder{

        private TextView place_name;
        private ImageView img;

        SearchPlaceHolder(@NonNull View itemView) {
            super(itemView);
            this.place_name = itemView.findViewById(R.id.place_name);
            this.img = itemView.findViewById(R.id.place_icon);
        }
    }
}
