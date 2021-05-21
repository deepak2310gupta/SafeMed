package com.example.safemed.FragmentsCollections;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.safemed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SixthFragment extends Fragment {


    Bitmap bmp6;
    ImageView medicineimageview6;
    Button selectButton6;
    Button predictButton2;
    Uri imageFileUri6;
    String fifthUri, fourthestUri;

    public SixthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sixth, container, false);
        medicineimageview6 = view.findViewById(R.id.medicineimageview6);
        selectButton6 = view.findViewById(R.id.selectButton6);
        predictButton2 = view.findViewById(R.id.predictButton2);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            fifthUri = getArguments().getString("uriFifth");
            fourthestUri = getArguments().getString("uriFourth");
        }
        selectButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 600);
            }
        });

        predictButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageFileUri6 == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("You Have To Upload The Components Image To Continue");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                } else {
                    Toast.makeText(getContext(), "UriFourth::" + fourthestUri + "UriFifth::" + fifthUri + "SixthUri::" + bmp6, Toast.LENGTH_SHORT).show();
                    loadImages();
                }
            }
        });


        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 600) {
            imageFileUri6 = data.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp6 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri6), null, bmpFactoryOptions);
                bmpFactoryOptions.inSampleSize = 2;
                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp6 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri6), null, bmpFactoryOptions);
                medicineimageview6.setImageBitmap(bmp6);
                Toast.makeText(getContext(), "" + bmp6, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }


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
                            //test1.setImageBitmap(bitmap);
                        }

                        if (finalI == 2) {
                            //test2.setImageBitmap(bitmap);
                        }

                        if (finalI == 3) {
                            //  test3.setImageBitmap(bitmap);
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
}