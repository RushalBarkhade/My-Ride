package com.example.myride;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myride.firebase.Firebase;
import com.example.myride.recycle_views.SearchRecycleView;
import com.example.myride.roomDB.RoomDB;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractActivity extends AppCompatActivity implements SearchRecycleView.ClickListener, View.OnClickListener {

    public ExecutorService service;
    public RoomDB db;
    protected FirebaseAnalytics mFirebaseAnalytics;
    private Firebase firebase;

    private String LoginInUserKey;
    private String userKey="abc";

    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAY = "DAY";
    public static final String SOURCE_NAME = "SOURCE_NAME";
    public static final String DESTINATION_NAME = "DESTINATION_NAME";
    public static final String FLAG="FLAG";
    public static final String RESULT = "RESULT";

    protected static final int SEARCH_COMPLETION_ID = 1;
    protected static final int AUTOCOMPLETE_REQUEST_CODE = 2;
    public static final int UPLOAD_IMAGE_ID = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+4);
        db = RoomDB.getInstance(getApplicationContext());
        //mFirebaseAnalytics= FirebaseAnalytics.getInstance(this);
        this.firebase = Firebase.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_COMPLETION_ID) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String placename = data.getStringExtra(RESULT);
                char flag = data.getCharExtra(FLAG, 'n');
                assert placename != null;
                getValues(placename, flag);
            }
        }
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //assert data != null;
                Place place = Autocomplete.getPlaceFromIntent(data);
                //placename = place.getName();
                getValues(place.getName(), 'n');
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);
                Toast.makeText(this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();
                assert status.getStatusMessage() != null;
                Log.i("TAG", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
    List<String> imagePathList = new ArrayList<>();

    public void getImageFilePath(Uri uri) {

        File file = new File(uri.getPath());
        String[] filePath = file.getPath().split(":");
        String image_id = filePath[filePath.length - 1];

        Cursor cursor = getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor!=null) {
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            imagePathList.add(imagePath);
            cursor.close();
        }
    }

    protected int calculateWidth(View view){
        return view.getMeasuredWidth();
    }

    public Firebase getFirebase(){
        return this.firebase;
    }

    public abstract int getLayout();
    public abstract void getValues(Object values,char flag);

    public String getLoginInUserKey() {
        return LoginInUserKey;
    }

    public void setLoginInUserKey(String loginInUserKey) {
        LoginInUserKey = loginInUserKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
