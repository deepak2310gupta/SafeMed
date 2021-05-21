package com.example.safemed.FragmentsCollections;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.safemed.R;

import java.io.FileNotFoundException;


public class SecondFragment extends Fragment {


    Bitmap bmp2;
    ImageView medicineimageview2;
    Button selectButton2;
    Button saveAndProceedSecond;
    Uri imageFileUri2;
    String uriOne;

    public SecondFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        selectButton2 = view.findViewById(R.id.selectButton2);
        medicineimageview2 = view.findViewById(R.id.medicineimageview2);
        saveAndProceedSecond = view.findViewById(R.id.saveAndProceedSecond);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            uriOne = getArguments().getString("uriOne");
        }

        selectButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent1.setType("image/*");
                startActivityForResult(intent1, 200);
            }
        });
        saveAndProceedSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageFileUri2 == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("You Have To Upload The Barcode Image To Continue");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("uriSecond", "" + bmp2);
                    bundle.putString("uriFirst", "" + uriOne);
                    ThirdFragment thirdFragment = new ThirdFragment();
                    thirdFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, thirdFragment).commit();

                }
            }
        });


        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            imageFileUri2 = data.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp2 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri2), null, bmpFactoryOptions);
                bmpFactoryOptions.inSampleSize = 2;
                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp2 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri2), null, bmpFactoryOptions);
                medicineimageview2.setImageBitmap(bmp2);
                //.makeText(getContext(), ""+bmp2, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }


    }
}