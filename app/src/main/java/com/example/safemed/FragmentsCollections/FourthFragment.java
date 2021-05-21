package com.example.safemed.FragmentsCollections;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.example.safemed.FragmentsCollections.FifthFragment;
import com.example.safemed.R;

import java.io.FileNotFoundException;

import static android.content.Context.MODE_PRIVATE;


public class FourthFragment extends Fragment {


    Bitmap bmp4;
    ImageView medicineimageview4;
    Button selectButton4;
    Button saveAndProceedFourth;
    Uri imageFileUri4;

    public FourthFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        saveAndProceedFourth = view.findViewById(R.id.saveAndProceedFourth);
        medicineimageview4 = view.findViewById(R.id.medicineimageview4);
        selectButton4 = view.findViewById(R.id.selectButton4);

        showInfoDialog();
        selectButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 400);
            }
        });

        saveAndProceedFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageFileUri4 == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("You Have To Upload The Medicine Image To Continue.");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("uriFourth", "" + bmp4);
                    FifthFragment sixthFragment = new FifthFragment();
                    sixthFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, sixthFragment).commit();
                }
            }
        });


        return view;
    }


    private boolean showFirstTimeDetails() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("prefsDetails", MODE_PRIVATE);
        boolean firstStartNewAgain = sharedPreferences.getBoolean("isfirstStart", true);

        if (firstStartNewAgain == true) {

            SharedPreferences preferences = getContext().getSharedPreferences("prefsDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isfirstStart", false);
            editor.apply();
            return true;
        }
        return firstStartNewAgain;
    }

    private void showInfoDialog() {

        if (showFirstTimeDetails()) {

            SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Here's A Information!");
            pDialog.setContentText("You Have To Upload 3 Images In The Respective Order. \n1.Medicine Image\n2.Barcode Image\n3.Components Image");
            pDialog.setConfirmText("OKAY");
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                }
            })
                    .show();
        } else {
            return;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 400) {
            imageFileUri4 = data.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp4 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri4), null, bmpFactoryOptions);
                bmpFactoryOptions.inSampleSize = 2;
                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp4 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri4), null, bmpFactoryOptions);
                medicineimageview4.setImageBitmap(bmp4);
                // Toast.makeText(getContext(), ""+bmp4, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }


    }
}