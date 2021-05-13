package com.TugasPAPBL.tes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button mBtn_lengkapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String firstTime = sharedPreferences.getString("FirstTimeInstall", "");
        if (firstTime.equals("Yes")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }

        //inisial tombol
        mBtn_lengkapi = findViewById(R.id.btn_lengkapi);

        // function tombol
        mBtn_lengkapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lengkapi = new Intent(getApplicationContext(), Step1_Activity.class);
                startActivity(lengkapi);
            }
        });


    }


}
