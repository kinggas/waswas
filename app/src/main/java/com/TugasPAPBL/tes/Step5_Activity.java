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

public class Step5_Activity extends AppCompatActivity {

    DBHandler dbHandler;
    private Toolbar toolbar;
    private Button btn_lanjut;
    private EditText et_tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step5_);

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
        et_tb = findViewById(R.id.editText_tb);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        dbHandler = new DBHandler(this);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_tb.getText().length() == 0) {
                    et_tb.setError("Isi tinggimu dulu ya :(");
                } else {
                    //dtbs
                    SQLiteDatabase db = dbHandler.getWritableDatabase();
                    String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET " +
                            User.UserDetails.COL_TB + " = '" + et_tb.getText().toString() + "' WHERE id=1";
                    db.execSQL(query);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Oke~", Toast.LENGTH_SHORT).show();

                    Intent kelangkah6 = new Intent(getApplicationContext(), Step6_Activity.class);
                    startActivity(kelangkah6);
                }
            }
        });

    }
}
