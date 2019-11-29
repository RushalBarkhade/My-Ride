package com.example.myride.recycle_views;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.R;
import com.example.myride.firebase.UserReview;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewRecycleAdapter extends RecyclerView.Adapter<ReviewRecycleAdapter.ReviewRecycleHolder>  {

    private List<UserReview> reviews;

    public ReviewRecycleAdapter(List<UserReview> rideDetails) {
        this.reviews = rideDetails;
    }

    @NonNull
    @Override
    public ReviewRecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycle_review_card,parent,false);
        return new ReviewRecycleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewRecycleHolder holder, int position) {
        UserReview review = reviews.get(position);
        holder.reviewText.setText(review.getReviewText());
        holder.reviewUserName.setText(review.getUserName());
        if (review.getVerification()) {
            holder.reviewUserVerification.setText("Verified");
            holder.reviewUserVerification.setTextColor(Color.BLUE);
        }else{
            holder.reviewUserVerification.setText("Not Verified");
            holder.reviewUserVerification.setTextColor(Color.RED);
        }
        holder.reviewRating.setRating(review.getUserReviewInFloat());
        holder.reviewRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        holder.reviewMood.setText("Very bad");
                        break;
                    case 2:
                        holder.reviewMood.setText("Need some improvement");
                        break;
                    case 3:
                        holder.reviewMood.setText("Good");
                        break;
                    case 4:
                        holder.reviewMood.setText("Great");
                        break;
                    case 5:
                        holder.reviewMood.setText("Awesome. I love it");
                        break;
                    default:
                        holder.reviewMood.setText("");
                }
            }
        });
            }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewRecycleHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView;
        TextView reviewUserName;
        TextView reviewText;
        RatingBar reviewRating;
        TextView reviewUserVerification;
        TextView reviewMood;

        ReviewRecycleHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.review_user_img);
            reviewUserName = itemView.findViewById(R.id.review_user_name);
            reviewText = itemView.findViewById(R.id.user_review_txt);
            reviewRating = itemView.findViewById(R.id.rating_id);
            reviewUserVerification = itemView.findViewById(R.id.verificarion_id);
            reviewMood = itemView.findViewById(R.id.rating_mood);
        }
    }
}
