package com.TugasPAPBL.tes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Finish_Activity extends AppCompatActivity {

    private Button btn_cekprofil;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_);

        //inisialisasi
        btn_cekprofil = findViewById(R.id.btn_cekprofil);

        btn_cekprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keresult = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(keresult);
            }
        });
        //View Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
