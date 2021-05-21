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


public class FifthFragment extends Fragment {


    Bitmap bmp5;
    ImageView medicineimageview5;
    Button selectButton5;
    Button saveAndProceedFifth;
    Uri imageFileUri5;
    String Fourthuri;

    public FifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fifth, container, false);
        saveAndProceedFifth = view.findViewById(R.id.saveAndProceedFifth);
        medicineimageview5 = view.findViewById(R.id.medicineimageview5);
        selectButton5 = view.findViewById(R.id.selectButton5);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            Fourthuri = getArguments().getString("uriFourth");
        }
        selectButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 500);
            }
        });


        saveAndProceedFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageFileUri5 == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("You Have To Upload The Barcode Image To Continue.");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("uriFifth", "" + bmp5);
                    bundle.putString("uriFourth", "" + Fourthuri);
                    SixthFragment sixthFragment = new SixthFragment();
                    sixthFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, sixthFragment).commit();

                }
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 500) {
            imageFileUri5 = data.getData();
            try {
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                bmp5 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri5), null, bmpFactoryOptions);
                bmpFactoryOptions.inSampleSize = 2;
                bmpFactoryOptions.inJustDecodeBounds = false;
                bmp5 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageFileUri5), null, bmpFactoryOptions);
                medicineimageview5.setImageBitmap(bmp5);
                // Toast.makeText(getContext(), ""+bmp5, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }


    }
}