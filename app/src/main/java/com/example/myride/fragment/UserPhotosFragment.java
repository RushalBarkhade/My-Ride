package com.example.myride.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myride.R;
import com.example.myride.recycle_views.UserRideAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserPhotosFragment extends AbstractFragment  {

    private Uri filePath;

    public UserPhotosFragment(){

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.ride_fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == activity.UPLOAD_IMAGE_ID) {
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), filePath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
//                if (data.getClipData()!=null){
//                    int count = data.getClipData().getItemCount();
//                    for (int i = 0;i<count;i++){
//            -            Uri imageUri = data.getClipData().getItemAt(i).getUri();
//                        getImageFilePath(imageUri);
//                    }
//                }else if (data.getData()!=null){
//                    Uri uri = data.getData();
//                    getImageFilePath(uri);
//                }

        }
    }

    private void setLayout(final View view){
        activity.getFirebase().getRideImages().
                orderByKey().
                equalTo(activity.getUserKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()>0){
                    activity.service.execute(new Runnable() {
                        @Override
                        public void run() {
                            final List<String> rideImages = new ArrayList<>();
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                String uri = (String) snapshot.getValue();
                                rideImages.add(uri);
                            }
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    UserRideAdapter recycle = new UserRideAdapter(getContext(),rideImages);
                                    RecyclerView recycleView = setRecycleView(view);
                                    recycleView.setAdapter(recycle);
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
                }else
                    setEmptyTextView(view);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        setLayout(view);


//        Button button = view.findViewById(R.id.test_button);
//        Button upload = view.findViewById(R.id.upload_image);
//        final ImageView imageView = view.findViewById(R.id.imageView);
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadFile();
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent();
//                intent.setType("image/*");
//                //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"), activity.UPLOAD_IMAGE_ID);
//               // imageView.setImageBitmap(activity.getImage());
//            }
//        });
    }

    private void uploadFile() {
        //if there is a file to upload
        //if (filePath != null) {
                //displaying a progress dialog while upload is going on
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Uploading");
                progressDialog.show();
                FirebaseStorage storage;
                StorageReference storageReference;

                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();

                //StorageReference riversRef = activity.getFirebase().getStorageReference().getReference();
                //StorageReference childRef = riversRef.child("image");

                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.login_bk);
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitMapData = stream.toByteArray();

                StorageReference reff = storageReference.child("image1");
                //UploadTask uploadTask = childRef.putBytes(bitMapData);
                reff.putBytes(bitMapData).
                        addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                        while (!task.isSuccessful());
                        Uri downloadUri = task.getResult();
                        Log.v("TAG",downloadUri.toString());

                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+(int)progress+"%");
                            }
                        });
        }
    //}
}