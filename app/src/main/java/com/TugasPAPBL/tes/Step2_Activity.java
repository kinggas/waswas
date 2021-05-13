package com.TugasPAPBL.tes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.TugasPAPBL.tes.sqlite.DBHandler;
import com.TugasPAPBL.tes.sqlite.User;

public class Step2_Activity extends AppCompatActivity {

    DBHandler dbHandler;
    private Toolbar toolbar;
    private Button btn_gpria, btn_gwanita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2_);

        //View
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

        //inisial tombol
        btn_gpria = findViewById(R.id.btn_gPria);
        btn_gwanita = findViewById(R.id.btn_gWanita);

        dbHandler = new DBHandler(this);

        // function tombol
        btn_gpria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jk = "Pria";
                SQLiteDatabase db = dbHandler.getWritableDatabase();
                String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET "
                        + User.UserDetails.COL_JK + " = '" + jk + "' WHERE id=1";
                db.execSQL(query);
                finish();
                Toast.makeText(getApplicationContext(), "Oke~", Toast.LENGTH_SHORT).show();
                Intent gPria = new Intent(getApplicationContext(), Step3_Activity.class);
                startActivity(gPria);
            }
        });

        btn_gwanita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jk = "Wanita";
                SQLiteDatabase db = dbHandler.getWritableDatabase();
                String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET "
                        + User.UserDetails.COL_JK + " = '" + jk + "' WHERE id=1";
                db.execSQL(query);
                db.close();
                finish();
                Toast.makeText(getApplicationContext(), "Oke~", Toast.LENGTH_SHORT).show();
                Intent gWanita = new Intent(getApplicationContext(), Step3_Activity.class);
                startActivity(gWanita);
            }
        });
    }
}
