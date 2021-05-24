package com.example.safemed.FragmentsCollections;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.example.safemed.R;
import com.example.safemed.ml.Mymodel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;


public class SixthFragment extends Fragment {
    Bitmap bmp6;
    ImageView medicineimageview6;
    Button selectButton6;
    Button predictButton2;
    Uri imageFileUri6;
     ProgressDialog progressDialog;
    double narray1[] = new double[224];
    double narray2[] = new double[224];
    double narray3[] = new double[224];
    double ncomparearray[] = new double[224];
    ImageView test4n,test5n,test6n;
    public SixthFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sixth, container, false);
        medicineimageview6 = view.findViewById(R.id.medicineimageview6);
        selectButton6 = view.findViewById(R.id.selectButton6);
        predictButton2 = view.findViewById(R.id.predictButton2);
        progressDialog=new ProgressDialog(getContext());
        test4n=view.findViewById(R.id.test4n);
        test5n=view.findViewById(R.id.test5n);
        test6n=view.findViewById(R.id.test6n);


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
                    SweetAlertDialog sweetAlertDialog=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setContentText("You Have To Upload The Medicine Image To Continue");
                    sweetAlertDialog.setConfirmText("Okay");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                    sweetAlertDialog.show();
                }
                else {

                    progressDialog.setTitle("Authenticating Medicine");
                    progressDialog.setMessage("Please Wait.....");
                    progressDialog.show();


                    Bitmap bitmap4= ((BitmapDrawable)test4n.getDrawable()).getBitmap();
                    Bitmap bitmap5= ((BitmapDrawable)test5n.getDrawable()).getBitmap();
                    Bitmap bitmap6= ((BitmapDrawable)test6n.getDrawable()).getBitmap();
                    bitmap4 = Bitmap.createScaledBitmap(bitmap4, 224, 224, true);
                    bitmap5 = Bitmap.createScaledBitmap(bitmap5, 224, 224, true);
                    bitmap6 = Bitmap.createScaledBitmap(bitmap6, 224, 224, true);

                    try {
                        Mymodel model = Mymodel.newInstance(getContext());
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                        tensorImage.load(bitmap4);
                        ByteBuffer byteBuffer = tensorImage.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        Mymodel.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            narray1[i] = outputFeature0.getFloatArray()[i];
                        }
                        model.close();

                    }
                    catch (IOException e) {

                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    try {
                        Mymodel model = Mymodel.newInstance(getContext());
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                        tensorImage.load(bitmap5);
                        ByteBuffer byteBuffer = tensorImage.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        Mymodel.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            narray2[i] = outputFeature0.getFloatArray()[i];
                        }
                        model.close();

                    }
                    catch (IOException e) {

                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    try {
                        Mymodel model = Mymodel.newInstance(getContext());
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                        tensorImage.load(bitmap6);
                        ByteBuffer byteBuffer = tensorImage.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        Mymodel.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            narray3[i] = outputFeature0.getFloatArray()[i];
                        }
                        model.close();

                    }
                    catch (IOException e) {

                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    bmp6 = Bitmap.createScaledBitmap(bmp6, 224, 224, true);
                    try {
                        Mymodel model1 = Mymodel.newInstance(getContext());
                        TensorBuffer inputFeature01 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tensorImage1 = new TensorImage(DataType.FLOAT32);
                        tensorImage1.load(bmp6);
                        ByteBuffer byteBuffer1 = tensorImage1.getBuffer();
                        inputFeature01.loadBuffer(byteBuffer1);
                        Mymodel.Outputs outputs1 = model1.process(inputFeature01);
                        TensorBuffer outputFeature0 = outputs1.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            ncomparearray[i] = outputFeature0.getFloatArray()[i];
                        }
                        model1.close();

                    }
                    catch (IOException e) {

                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    double myFinalAnswer1n=rmsValue(narray1,ncomparearray);
                    double myFinalAnswer2n=rmsValue(narray2,ncomparearray);
                    double myFinalAnswer3n=rmsValue(narray3,ncomparearray);
                    Bundle bundle = new Bundle();
                    bundle.putDouble("myTrainingModelValue1n",myFinalAnswer1n);
                    bundle.putDouble("myTrainingModelValue2n",myFinalAnswer2n);
                    bundle.putDouble("myTrainingModelValue3n",myFinalAnswer3n);
                    ResultSecondFragment resultSecondFragment = new ResultSecondFragment();
                    resultSecondFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, resultSecondFragment).commit();
                    progressDialog.dismiss();


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
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }
    }
    public static double rmsValue(double arr1[], double arr2[]) {
        double square = 0;
        double mean = 0;
        double root = 0;

        for (int i = 0; i < 224; i++) {
            square += Math.pow((arr1[i] - arr2[i]), 2);
        }

        root = (float) Math.sqrt(square);

        return root;
    }


}