package com.example.datta.barterapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//observe Manifest  file.. ( defining parentactivity is imp)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// getting Splash Screen
        Handler handler=new Handler();

        Runnable r=new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
            }
        };
        handler.postDelayed(r, 3000);
    }
}
