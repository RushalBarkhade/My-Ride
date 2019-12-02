package com.example.myride.firebase;

public class UserReview {
    private String userName;
    private Boolean verification;
    private String reviewText;
    private float userReviewInFloat;
    private RideTime time;

    public UserReview(){}

    public UserReview(String userName, Boolean verification, String reviewText, float userReviewInFloat,RideTime time) {
        this.userName = userName;
        this.verification = verification;
        this.reviewText = reviewText;
        this.userReviewInFloat = userReviewInFloat;
        this.time= time;
    }

    public float getUserReviewInFloat() {
        return userReviewInFloat;
    }

    public void setUserReviewInFloat(float userReviewInFloat) {
        this.userReviewInFloat = userReviewInFloat;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RideTime getTime() {
        return time;
    }

    public void setTime(RideTime time) {
        this.time = time;
    }
}
