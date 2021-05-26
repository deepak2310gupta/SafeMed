package com.example.safemed.FragmentsCollections;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.safemed.R;

public class ResultFragment extends Fragment {


    public ResultFragment() {
        // Required empty public constructor
    }

    double answer1, answer2, answer3;
    TextView textViewResultsDesc;
    ImageButton btnback;
    LottieAnimationView answersImageCorrect, answersImageWrong;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_result, container, false);
        btnback=view.findViewById(R.id.btnback);
        answersImageWrong = view.findViewById(R.id.answersImageWrong);
        answersImageCorrect = view.findViewById(R.id.answersImageCorrect);
        textViewResultsDesc = view.findViewById(R.id.textViewResultsDesc);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            answer1 = bundle.getDouble("myTrainingModelValue1", 0.0d);
            answer2 = bundle.getDouble("myTrainingModelValue2", 0.1d);
            answer3 = bundle.getDouble("myTrainingModelValue3", 0.2d);

        }


        if (answer1 <= 0.3 || answer2 <= 0.3 || answer3 <= 0.3) {
            answersImageCorrect.setVisibility(View.VISIBLE);
            answersImageWrong.setVisibility(View.GONE);
            textViewResultsDesc.setTextColor(getResources().getColor(R.color.correct));
            textViewResultsDesc.setText("The Medicine Is Genuine");
        } else {
            answersImageWrong.setVisibility(View.VISIBLE);
            answersImageCorrect.setVisibility(View.GONE);
            textViewResultsDesc.setTextColor(getResources().getColor(R.color.wrong));
            textViewResultsDesc.setText("The Medicine Is Fake");
        }


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeMedicineFragment fragment2 = new FakeMedicineFragment();

                getFragmentManager().beginTransaction().replace(R.id.collectionfragmentsReplacer, fragment2).commit();

            }
        });



        return view;
    }


}