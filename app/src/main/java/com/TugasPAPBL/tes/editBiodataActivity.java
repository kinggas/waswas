package com.TugasPAPBL.tes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.TugasPAPBL.tes.sqlite.DBHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class editBiodataActivity extends AppCompatActivity {
    protected EditText editTextNama, editTextTanggalLahir, editTextTinggiBadan, editTextBeratBadan;
    protected Spinner spinnerBiodataJenisKelamin, spinnerLevelAktivitas;
    private Button btnSimpan;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ImageView img_info;
    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_biodata);
        ArrayList<EditModel> list = new ArrayList<>();

        String[] editText = new String[7];
        final DBHandler dbHandler = new DBHandler(this);
        Cursor cursorEditBiodata = dbHandler.getAllData();
        if (cursorEditBiodata.moveToFirst()) {
            editText[0] = cursorEditBiodata.getString(1); //nama
            editText[1] = cursorEditBiodata.getString(2); //jenisKelamin
            editText[2] = cursorEditBiodata.getString(3); //tanggalLahir
            editText[3] = cursorEditBiodata.getString(4); //beratBadan
            editText[4] = cursorEditBiodata.getString(5); //tinggiBadan
            editText[5] = cursorEditBiodata.getString(6); //levelAktivitas
        }
        EditModel editModel = new EditModel(editText[0], editText[2], editText[4], editText[3], editText[1], editText[5]);
        list.add(editModel);
        dbHandler.close();
        btnSimpan = findViewById(R.id.btn_editBiodata);

        editTextNama = findViewById(R.id.editBiodataNama);
        editTextTanggalLahir = findViewById(R.id.editBiodataTanggalLahir);
        editTextTinggiBadan = findViewById(R.id.editBiodataTinggiBadan);
        editTextBeratBadan = findViewById(R.id.editBiodataBeratBadan);
        spinnerBiodataJenisKelamin = findViewById(R.id.spinnerGenderEditBiodata);
        spinnerLevelAktivitas = findViewById(R.id.editBiodataUmurLevelAktivitas);

        //create a list of items for the spinner.
        String[] itemsEBJk = new String[]{"Pria", "Wanita"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapterEBJk = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_edit_biodata, itemsEBJk);
        adapterEBJk.setDropDownViewResource(R.layout.spinner_list_edit_biodata);
        //set the spinners adapter to the previously created one.
        spinnerBiodataJenisKelamin.setAdapter(adapterEBJk);

        //create a list of items for the spinner.
        String[] itemsEBLevelAktivitas = new String[]{"Tidak Olahraga", "Olahraga Ringan", "Olahraga Sedang", "Olahraga Berat", "Olahraga 2x Sehari"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        // There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapterEBLevelAktivitas = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_edit_biodata, itemsEBLevelAktivitas);
        adapterEBLevelAktivitas.setDropDownViewResource(R.layout.spinner_list_edit_biodata);
        //set the spinners adapter to the previously created one.
        spinnerLevelAktivitas.setAdapter(adapterEBLevelAktivitas);
        Log.d("print", "yes");


        editTextNama.setText(editModel.getEditTextNama());
        editTextTanggalLahir.setText(editModel.getEditTextTanggalLahir());
        editTextTinggiBadan.setText(editModel.geteditTextTinggiBadan());
        editTextBeratBadan.setText(editModel.geteditTextBeratBadan());
        spinnerBiodataJenisKelamin.setSelection(editModel.getspinnerBiodataJenisKelamin());
        spinnerLevelAktivitas.setSelection(editModel.getspinnerLevelAktivitas());

        editTextTanggalLahir.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_date_range_black_24dp, 0, 0, 0);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] updateDataEditBiodata = getUpdateDataEditBiodata();

                dbHandler.updateData("1", updateDataEditBiodata[0], updateDataEditBiodata[4], updateDataEditBiodata[1], Integer.parseInt(updateDataEditBiodata[3]), Integer.parseInt(updateDataEditBiodata[2]), updateDataEditBiodata[5]);
                Intent intent = new Intent(getApplicationContext(), biodataActivity.class);
                startActivity(intent);
            }
        });
        editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        //DIALOG
        mContext = getApplicationContext();
        mActivity = editBiodataActivity.this;

        mRelativeLayout = findViewById(R.id.activity_edit_biodata);
        img_info = findViewById(R.id.img_info);

        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.dialog_layout, null);
                mPopupWindow = new PopupWindow(
                        customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }
                ImageButton close = customView.findViewById(R.id.ib_close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
            }
        });
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        //calendar
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextTanggalLahir.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public String[] getUpdateDataEditBiodata() {
        String[] updateDataEditBiodata = new String[7];
        updateDataEditBiodata[0] = String.valueOf(editTextNama.getText());
        updateDataEditBiodata[1] = String.valueOf(editTextTanggalLahir.getText());
        updateDataEditBiodata[2] = String.valueOf(editTextTinggiBadan.getText());
        updateDataEditBiodata[3] = String.valueOf(editTextBeratBadan.getText());
        updateDataEditBiodata[4] = String.valueOf(spinnerBiodataJenisKelamin.getSelectedItem());
        updateDataEditBiodata[5] = String.valueOf(spinnerLevelAktivitas.getSelectedItem());

        return updateDataEditBiodata;
    }
}