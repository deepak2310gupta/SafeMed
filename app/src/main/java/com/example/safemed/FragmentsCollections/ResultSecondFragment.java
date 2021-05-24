package com.example.safemed.FragmentsCollections;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.safemed.R;


public class ResultSecondFragment extends Fragment {


    public ResultSecondFragment() {
        // Required empty public constructor
    }
    double answer1n, answer2n, answer3n;
    TextView textViewResultsDescn;
    ImageButton btnbackn;
    LottieAnimationView answersImageCorrectn, answersImageWrongn;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_result_second, container, false);
        btnbackn=view.findViewById(R.id.btnbackn);
        answersImageWrongn = view.findViewById(R.id.answersImageWrongn);
        answersImageCorrectn = view.findViewById(R.id.answersImageCorrectn);
        textViewResultsDescn = view.findViewById(R.id.textViewResultsDescn);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            answer1n = bundle.getDouble("myTrainingModelValue1n", 0.4d);
            answer2n = bundle.getDouble("myTrainingModelValue2n", 0.5d);
            answer3n = bundle.getDouble("myTrainingModelValue3n", 0.6d);

        }


        btnbackn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeMedicineFragment fragment2 = new FakeMedicineFragment();
                getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, fragment2).commit();

            }
        });


        if (answer1n <= 0.3 || answer2n <= 0.3 || answer3n <= 0.3) {
            answersImageCorrectn.setVisibility(View.VISIBLE);
            answersImageWrongn.setVisibility(View.GONE);
            textViewResultsDescn.setTextColor(getResources().getColor(R.color.correct));
            textViewResultsDescn.setText("The Medicine Is Genuine");
        }
        else {
            answersImageWrongn.setVisibility(View.VISIBLE);
            answersImageCorrectn.setVisibility(View.GONE);
            textViewResultsDescn.setTextColor(getResources().getColor(R.color.wrong));
            textViewResultsDescn.setText("The Medicine Is Fake");
        }


        return view;
    }



}