package com.example.safemed.FragmentsCollections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.safemed.R;

public class MedicineDetectionOneFragment extends Fragment {


    public MedicineDetectionOneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicine_detection_one, container, false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ThirdFragment thirdFragment = new ThirdFragment();
        ft.replace(R.id.collectionfragmentsReplacer, thirdFragment);
        ft.commit();
        return view;
    }


}