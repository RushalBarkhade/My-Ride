package com.example.myride.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myride.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UserPhotosFragment extends AbstractFragment  {

    public UserPhotosFragment(){
        Log.v("TAG","HELLLLLLLLLO");
    }
    @Override
    protected int getFragmentLayout() {
        return R.layout.test_layout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == activity.UPLOAD_IMAGE_ID) {
            Log.v("TAG", "UPLOAD_IMAGE");
                Log.v("TAG", "HELOOOOO");
                filePath = data.getData();
                Log.v("TAGG", filePath.toString() + "jjj");
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
    private Uri filePath;

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        Button button = view.findViewById(R.id.test_button);
        Button upload = view.findViewById(R.id.upload_image);
        final ImageView imageView = view.findViewById(R.id.imageView);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setType("image/*");
                //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), activity.UPLOAD_IMAGE_ID);
               // imageView.setImageBitmap(activity.getImage());
            }
        });
    }

    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
                //displaying a progress dialog while upload is going on
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Uploading");
                progressDialog.show();

                StorageReference riversRef = activity.getFirebase().getStorageReference().getReference();
                StorageReference childRef = riversRef.child("image");
                UploadTask uploadTask = childRef.putFile(filePath);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                //calculating progress percentage
                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                                //displaying percentage in progress dialog
                                progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                            }
                        });
        }
    }
}