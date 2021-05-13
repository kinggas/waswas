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

public class Step4_Activity extends AppCompatActivity {

    DBHandler dbHandler;
    private Toolbar toolbar;
    private Button btn_lanjut;
    private EditText et_bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4_);

        //View Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set icon ke toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //inisialisasi
        et_bb = findViewById(R.id.editText_bb);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        dbHandler = new DBHandler(this);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_bb.getText().length() == 0) {
                    et_bb.setError("Isi beratmu dulu ya :(");
                } else {
                    SQLiteDatabase db = dbHandler.getWritableDatabase();
                    String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET " +
                            User.UserDetails.COL_BB + " = '" + et_bb.getText().toString() + "' WHERE id=1";
                    db.execSQL(query);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Oke~", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent kelangkah5 = new Intent(getApplicationContext(), Step5_Activity.class);
                    startActivity(kelangkah5);
                }
            }
        });

    }
}
