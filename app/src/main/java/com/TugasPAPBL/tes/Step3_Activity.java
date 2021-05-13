package com.TugasPAPBL.tes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.TugasPAPBL.tes.sqlite.DBHandler;
import com.TugasPAPBL.tes.sqlite.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Step3_Activity extends AppCompatActivity {

    DBHandler dbHandler;
    private Toolbar toolbar;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText et_tgllahir;
    private Button btn_lanjut;
    private TextView tvDateResult;

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3_);

        et_tgllahir = findViewById(R.id.editText_tgllahir);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        tvDateResult = findViewById(R.id.editText_tgllahir);
        dbHandler = new DBHandler(this);

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

        //calendar
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        et_tgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        //btn lanjut
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_tgllahir.getText().toString().length() == 0) {
                    et_tgllahir.setError("Isi tanggal lahir dulu ya :(");
                } else {

                    //dtbs
                    SQLiteDatabase db = dbHandler.getWritableDatabase();
                    String query = "UPDATE " + User.UserDetails.TABLE_NAME + " SET " +
                            User.UserDetails.COL_TGL + " = '" + et_tgllahir.getText().toString() + "' WHERE id=1";
                    db.execSQL(query);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Oke~", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent kelangkah4 = new Intent(getApplicationContext(), Step4_Activity.class);
                    startActivity(kelangkah4);
                }
            }
        });
    }

}

