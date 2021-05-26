package com.example.safemed.FragmentsCollections;

import android.app.ProgressDialog;
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


public class ThirdFragment extends Fragment {


    Bitmap bmp3;
    ImageView medicineimageview3, test2, test1, test3;
    Button selectButton3, predictButton;
    Uri imageFileUri3;

    double array1[] = new double[224];
    double array2[] = new double[224];
    double array3[] = new double[224];
    double comparearray[] = new double[224];
    ProgressDialog progressDialog;
    public ThirdFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third, container, false);
        medicineimageview3 = view.findViewById(R.id.medicineimageview3);

        selectButton3 = view.findViewById(R.id.selectButton3);
        predictButton = view.findViewById(R.id.predictButton);
        test1 = view.findViewById(R.id.test1);
        test2 = view.findViewById(R.id.test2);
        test3 = view.findViewById(R.id.test3);
        progressDialog=new ProgressDialog(getContext());

        Bitmap bitmap = ((BitmapDrawable)test1.getDrawable()).getBitmap();

        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);

        try {
            Mymodel model = Mymodel.newInstance(getContext());
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
            tensorImage.load(bitmap);
            ByteBuffer byteBuffer = tensorImage.getBuffer();
            inputFeature0.loadBuffer(byteBuffer);
            Mymodel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            for (int i = 0; i < 224; i++) {
                array1[i] = outputFeature0.getFloatArray()[i];
            }
            model.close();

        }
        catch (IOException e) {

            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }




        selectButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent2.setType("image/*");
                startActivityForResult(intent2, 300);
            }
        });
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (imageFileUri3 == null) {

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
                    predictButton.setText("Authenticating Medicine...");
                    progressDialog.setTitle("Authenticating Medicine");
                    progressDialog.setMessage("Please Wait.....");
                    progressDialog.show();

                    Bitmap bitmap2= ((BitmapDrawable)test2.getDrawable()).getBitmap();
                    Bitmap bitmap3= ((BitmapDrawable)test3.getDrawable()).getBitmap();
                    bitmap2 = Bitmap.createScaledBitmap(bitmap2, 224, 224, true);
                    bitmap3 = Bitmap.createScaledBitmap(bitmap3, 224, 224, true);

                    try {
                        Mymodel model = Mymodel.newInstance(getContext());
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                        tensorImage.load(bitmap2);
                        ByteBuffer byteBuffer = tensorImage.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        Mymodel.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            array2[i] = outputFeature0.getFloatArray()[i];
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
                        tensorImage.load(bitmap3);
                        ByteBuffer byteBuffer = tensorImage.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);
                        Mymodel.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            array3[i] = outputFeature0.getFloatArray()[i];
                        }
                        model.close();

                    }
                    catch (IOException e) {

                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    bmp3 = Bitmap.createScaledBitmap(bmp3, 224, 224, true);


                    try {
                        Mymodel model1 = Mymodel.newInstance(getContext());
                        TensorBuffer inputFeature01 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        TensorImage tensorImage1 = new TensorImage(DataType.FLOAT32);
                        tensorImage1.load(bmp3);
                        ByteBuffer byteBuffer1 = tensorImage1.getBuffer();
                        inputFeature01.loadBuffer(byteBuffer1);
                        Mymodel.Outputs outputs1 = model1.process(inputFeature01);
                        TensorBuffer outputFeature0 = outputs1.getOutputFeature0AsTensorBuffer();
                        for (int i = 0; i < 224; i++) {
                            comparearray[i] = outputFeature0.getFloatArray()[i];
                        }
                        model1.close();

                    }
                    catch (IOException e) {

                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }



                    double myFinalAnswer1=rmsValue(array1,comparearray);
                    double myFinalAnswer2=rmsValue(array2,comparearray);
                    double myFinalAnswer3=rmsValue(array3,comparearray);


                    Bundle bundle = new Bundle();
                    bundle.putDouble("myTrainingModelValue1",myFinalAnswer1);
                    bundle.putDouble("myTrainingModelValue2",myFinalAnswer2);
                    bundle.putDouble("myTrainingModelValue3",myFinalAnswer3);

                    ResultFragment fragment2 = new ResultFragment();
                    fragment2.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, fragment2).commit();
                    progressDialog.dismiss();

                }
            }
        });

        return view;
    }

    private StringBuffer HelpingObject(double[] array1) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < array1.length; i++)
            stringBuffer.append(array1[i]).append(",");
        return stringBuffer;
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
            } catch (FileNotFoundException e) {
                Log.v("ERROR", e.toString());
            }
        }
    }


    public  double rmsValue(double arr1[], double arr2[]) {
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