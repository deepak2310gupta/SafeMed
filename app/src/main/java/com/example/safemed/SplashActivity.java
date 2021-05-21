package com.example.safemed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView titleforApp;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        titleforApp = findViewById(R.id.titleforApp);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (haveNetwork()) {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else if (!haveNetwork()) {
                    Intent intent = new Intent(SplashActivity.this, NoInternet.class);
                    startActivity(intent);
                }

            }
        }, 3200);

        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        findViewById(R.id.cardSplash).startAnimation(animZoomIn);

    }


    private boolean haveNetwork() {
        boolean haveWiFi = false;
        boolean haveMobileData = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI")) {
                if (info.isConnected())
                    haveWiFi = true;
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected())
                    haveMobileData = true;
            }

        }
        return haveMobileData | haveWiFi;
    }
}