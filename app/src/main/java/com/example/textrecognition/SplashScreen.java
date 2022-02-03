package com.example.textrecognition;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //making full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //making full screen
        setContentView(R.layout.activity_splash_screen);
        ///progressBar = (ProgressBar) findViewById(R.id.progressBarid);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });

        thread.start();

    }

    public void doWork(){

        for(progress = 20; progress <= 60; progress = progress+20){

            try {
                Thread.sleep(1000);
                // progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }



    public void startApp(){
        Intent intent = new Intent(SplashScreen.this, MainActivity.class); //profile page create kore setar nam dite hobe....
        startActivity(intent);
        finish();

    }

    }
