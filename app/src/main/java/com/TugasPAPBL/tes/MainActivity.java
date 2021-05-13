package com.TugasPAPBL.tes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.TugasPAPBL.tes.sqlite.DBHandler;
import com.TugasPAPBL.tes.sqlite.KonsumsiHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private String TAG = "MainActivity";
    private KonsumsiHelper konsumsiHelper;
    private DBHandler dbHandler;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private double hKALORI_FINAL, hBMR;
    private int umur, kaloriPerHari, totalKonsumsiKalori = 0;
    private TextView txtKaloriSudahKonsum, txtKaloriSisa;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this, ResepActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(MainActivity.this, biodataActivity.class));
                    BottomNavigationView navView = findViewById(R.id.nav_view);
                    mTextMessage = findViewById(R.id.message);
                    navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    navView.getMenu().getItem(2).setChecked(true);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        TextView txtNama = findViewById(R.id.txt_namaDepan);
        TextView txtKaloriPerHari = findViewById(R.id.num_kaloriPerHari);


        //get "hello, nama "
        DBHandler dbHandler = new DBHandler(this);
        Cursor cursor = dbHandler.getAllData();
        String nama = null, jenisKelamin = null, tanggalLahir = null, tinggiBadan = null,
                beratBadan = null, levelAktivitas = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                nama = cursor.getString(1);
                jenisKelamin = cursor.getString(2);
                tanggalLahir = cursor.getString(3);
                beratBadan = cursor.getString(4);
                tinggiBadan = cursor.getString(5);
                levelAktivitas = cursor.getString(6);
            }
        }
        cursor.close();

        //hitung kalori
        int BB = Integer.parseInt(beratBadan);
        int TB = Integer.parseInt(tinggiBadan);
        hitungUmur(tanggalLahir);
        hitungKalori(BB, TB, umur, jenisKelamin, levelAktivitas);
        kaloriPerHari = (int) hKALORI_FINAL;
        txtKaloriPerHari.setText(Integer.toString(kaloriPerHari));

        //Konsumsi get id
        konsumsiHelper = new KonsumsiHelper(this);

        String[] namaDepan = nama.split("\\ ");
        txtNama.setText(namaDepan[0]);

    }

    private int hitungUmur(String tgl) {
        GregorianCalendar cal = new GregorianCalendar();
        int tahun_sekarang, bulan_sekarang, tanggal_sekarang, umur;
        int tahun_lahir = Integer.parseInt(tgl.substring(6, 10));
        int bulan_lahir = Integer.parseInt(tgl.substring(3, 5));
        int tanggal_lahir = Integer.parseInt(tgl.substring(0, 2));

        tahun_sekarang = cal.get(Calendar.YEAR);
        bulan_sekarang = cal.get(Calendar.MONTH);
        bulan_sekarang++;
        tanggal_sekarang = cal.get(Calendar.DAY_OF_MONTH);
        umur = tahun_sekarang - tahun_lahir - 1;
        if ((bulan_lahir < bulan_sekarang)
                || ((bulan_lahir == bulan_sekarang)
                && (tanggal_lahir < tanggal_sekarang))) {
            ++umur;
        }
        if (umur < 0)
            umur = 0;
        return umur;
    }

    private double hitungKalori(int BB, int TB, int umur, String jenisKelamin, String levelAktivitas) {
        String tampil_umur = String.valueOf(umur);
        //MENGHITUNG BMR
        switch (jenisKelamin) {
            case "Wanita":
                hBMR = Double.valueOf(447.593 + (9.247 * BB) + (3.098 * TB) - (4.33 * umur));
                break;
            case "Pria":
                hBMR = Double.valueOf(88.362 + (13.397 * BB) + (4.799 * TB) - (5.677 * umur));
                break;
        }

        //LVL_AKTIVITAS_FISIK (TEE)
        switch (levelAktivitas) {
            case "Tidak Olahraga":
                hKALORI_FINAL = hBMR * 1.2;
                break;
            case "Olahraga Ringan":
                hKALORI_FINAL = hBMR * 1.375;
                break;
            case "Olahraga Sedang":
                hKALORI_FINAL = hBMR * 1.55;
                break;
            case "Olahraga Berat":
                hKALORI_FINAL = hBMR * 1.725;
                break;
            case "Olahraga 2x Sehari":
                hKALORI_FINAL = hBMR * 1.9;
                break;
        }
        return hKALORI_FINAL;
    }


}
