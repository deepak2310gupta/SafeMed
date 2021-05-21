package com.example.safemed.FragmentsCollections;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safemed.R;

public class MedicineDetectionOneFragment extends Fragment {


    public MedicineDetectionOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicine_detection_one, container, false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FirstFragment secondFragment = new FirstFragment();
        ft.replace(R.id.collectionfragmentsReplacer, secondFragment);
        ft.commit();


        return view;
    }


}