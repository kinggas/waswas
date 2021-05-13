package com.TugasPAPBL.tes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.TugasPAPBL.tes.sqlite.DBHandler;
import com.TugasPAPBL.tes.sqlite.User;


public class Step1_Activity extends AppCompatActivity {

    DBHandler dbHandler;
    private Toolbar toolbar;
    private Button btn_lanjut;
    private EditText et_nama;
    private String textNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1_);

        //View Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set icon to toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //inisialisasi
        et_nama = findViewById(R.id.editText_nama);
        btn_lanjut = findViewById(R.id.btn_lanjut);

        dbHandler = new DBHandler(this);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_nama.getText().toString().length() == 0) {
                    et_nama.setError("Isi nama kamu dulu ya :(");
                } else {
                    try {
                        String nama = et_nama.getText().toString();
                        SQLiteDatabase db = dbHandler.getWritableDatabase();
                        String query = "Insert into " + User.UserDetails.TABLE_NAME + "(nama) values ('" + nama + "')";
                        db.execSQL(query);
                        db.close();
                        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                    Intent kelangkah2 = new Intent(getApplicationContext(), Step2_Activity.class);
                    startActivity(kelangkah2);
                }
            }
        });

    }

}
