package com.example.myride.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Firebase {

    private DatabaseReference reff;
    private FirebaseStorage storageReference;
    private static Firebase firebase;

    public static final String RIDE_NODE_LIST = "user";
    public static final String RIDE_NODE_KEY = "ride_key";
    public static final String RIDER_DETAILS = "riderDetails";
    public static final String REVIEW = "review";
    public static final String RIDER_REVIEW = "rider_review";
    public static final String RIDE_ASKER_REVIEW ="ride_asker_review";
    public static final String USER_RIDE_IMAGES="user_ride_images";


    private Firebase(){
        reff = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance();
    }
    public FirebaseStorage getStorageReference(){
        return this.storageReference;
    }

    public synchronized static Firebase getInstance(){
        if (firebase==null){
            firebase = new Firebase();
            return firebase;
        }
        return firebase;
    }

    public DatabaseReference getReff(){
        return this.reff;
    }
    /*
    Search key for the ride
     */
    public DatabaseReference getRidesSearchKeyNode(){
        return reff.child("USER_RIDE_ENTRY").child(RIDE_NODE_KEY);
    }
    /*
    Actual value on the list
     */
    public DatabaseReference getRiderList(){
        return reff.child("USER_RIDE_ENTRY").child(RIDE_NODE_LIST);
    }

    public DatabaseReference getRiderReview(){
        return reff.child(REVIEW).child(RIDER_REVIEW);
    }
    public DatabaseReference getAskerReview(){
        return reff.child(REVIEW).child(RIDER_REVIEW);
    }

    public DatabaseReference getRideImages(){
        return reff.child(USER_RIDE_IMAGES);
    }

    public DatabaseReference getRider(){
        return reff.child("USER_RIDE_ENTRY");
    }
    /*
       Generate private key user ride entry
    */
    public String getRidesKey(){
        return getRidesSearchKeyNode().push().getKey();
    }


}
