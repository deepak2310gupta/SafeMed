package com.example.safemed.FragmentsCollections;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safemed.R;

public class MedicineDetectionSecondFragment extends Fragment {


    public MedicineDetectionSecondFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicine_detection_second, container, false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SixthFragment sixthFragment = new SixthFragment();
        ft.replace(R.id.collectionfragmentsReplacer, sixthFragment);
        ft.commit();
        return view;
    }
}