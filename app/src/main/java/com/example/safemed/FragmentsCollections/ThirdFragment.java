package com.example.safemed.FragmentsCollections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.safemed.ModelMember;
import com.example.safemed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class ThirdFragment extends Fragment {



    Bitmap bmp3;
    ImageView medicineimageview3,test2,test1,test3;
    Button selectButton3,predictButton;
    Uri imageFileUri3;

    String oneUri,secUri;
    ArrayList<ModelMember>modelMemberArrayList;


    public ThirdFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_third, container, false);
        medicineimageview3=view.findViewById(R.id.medicineimageview3);

        modelMemberArrayList=new ArrayList<>();
        selectButton3=view.findViewById(R.id.selectButton3);
        predictButton=view.findViewById(R.id.predictButton);
        test1=view.findViewById(R.id.test1);
        test2=view.findViewById(R.id.test2);
        test3=view.findViewById(R.id.test3);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            oneUri=getArguments().getString("uriFirst");
            secUri=getArguments().getString("uriSecond");
        }
        selectButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent2.setType("image/*");
                startActivityForResult(intent2,300);
            }
        });

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(imageFileUri3==null){

                     AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("You Have To Upload The Components Image To Continue");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }

                 else{
                    loadImages();

                }

            }
        });



        return view;
    }

    private void loadImages() {

        for (int i = 1; i < 4; i++) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("remdesivir/rem" + i + ".jpeg");

            try {
                File localFile = File.createTempFile("rem" + i, "jpeg");
                int finalI = i;
                storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        if (finalI == 1) {
                            test1.setImageBitmap(bitmap);
                        }

                        if (finalI == 2) {
                            test2.setImageBitmap(bitmap);
                        }

                        if (finalI == 3) {
                            test3.setImageBitmap(bitmap);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 300) {
          imageFileUri3 = data.getData();

            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp3 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri3), null, bmpFactoryOptions);
                bmpFactoryOptions.inSampleSize = 2;
                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp3 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri3), null, bmpFactoryOptions);
                medicineimageview3.setImageBitmap(bmp3);
                Toast.makeText(getContext(), ""+bmp3, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }



    }






}