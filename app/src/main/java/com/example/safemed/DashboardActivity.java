package com.example.safemed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.safemed.FragmentsCollections.FakeMedicineFragment;
import com.example.safemed.FragmentsCollections.FakeNumberFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DashboardActivity extends AppCompatActivity {


    ChipNavigationBar menuChipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menuChipNavigationBar = findViewById(R.id.menuChipNavigationBar);

        FakeMedicineFragment fakeMedicineFragment = new FakeMedicineFragment();
        FragmentTransaction fragmentTransactionAgain1 = getSupportFragmentManager().beginTransaction();
        fragmentTransactionAgain1.replace(R.id.collectionfragmentsReplacer, fakeMedicineFragment, "").commit();

        menuChipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.fakeMedicine:
                        FakeMedicineFragment fakeMedicineFragment = new FakeMedicineFragment();
                        FragmentTransaction fragmentTransactionAgain1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionAgain1.replace(R.id.collectionfragmentsReplacer, fakeMedicineFragment, "").commit();
                        break;
                    case R.id.fakeNumber:
                        FakeNumberFragment fakeNumberFragment = new FakeNumberFragment();
                        FragmentTransaction fragmentTransactionAgain2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionAgain2.replace(R.id.collectionfragmentsReplacer, fakeNumberFragment, "").commit();
                        break;


                }

            }
        });


    }


}

