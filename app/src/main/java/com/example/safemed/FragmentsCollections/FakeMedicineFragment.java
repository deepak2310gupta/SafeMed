package com.example.safemed.FragmentsCollections;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.safemed.R;


public class FakeMedicineFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    String[] medicineslist = {"Please select the Medicine To Authenticate", "Remdesivir", "Tocilizumab"};
    Button uploadImagesButton;
    Spinner spin;
    ImageView represenatation;


    public FakeMedicineFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_fake_medicine, container, false);
        uploadImagesButton = view.findViewById(R.id.uploadImagesButton);
        represenatation = (ImageView) view.findViewById(R.id.represenatation);


        Glide.with(getContext()).load(R.drawable.safingmed).into(represenatation);





        spin = view.findViewById(R.id.medicinespinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, medicineslist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);
        uploadImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner mySpinner = (Spinner) view.findViewById(R.id.medicinespinner);
                int selectedItemOfMySpinner = mySpinner.getSelectedItemPosition();
                String actualPositionOfMySpinner = (String) mySpinner.getItemAtPosition(selectedItemOfMySpinner);
                if (selectedItemOfMySpinner <= 0) {
                    setSpinnerError(mySpinner, "Field Can Not Be Empty");
                } else {
                    if (actualPositionOfMySpinner.equals("Remdesivir")) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        MedicineDetectionOneFragment medicineDetectionOneFragment = new MedicineDetectionOneFragment();
                        ft.replace(R.id.collectionfragmentsReplacer, medicineDetectionOneFragment);
                        ft.commit();
                    } else {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        MedicineDetectionSecondFragment medicineDetectionSecondFragment = new MedicineDetectionSecondFragment();
                        ft.replace(R.id.collectionfragmentsReplacer, medicineDetectionSecondFragment);
                        ft.commit();
                    }
                }

            }
        });

        return view;
    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error");
            selectedTextView.setTextColor(Color.RED);
            selectedTextView.setText(error);
            spinner.performClick();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}