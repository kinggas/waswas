package com.TugasPAPBL.tes;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.TugasPAPBL.tes.sqlite.DBHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FragmentBiodata extends Fragment {

    private RecyclerView recyclerViewBiodata;
    private ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        return inflater.inflate(R.layout.fragment_biodata, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DBHandler dbHandler = new DBHandler(container.getContext());
        Cursor cursorEditBiodata = dbHandler.getAllData();
        String[] editText = new String[7];
        if (cursorEditBiodata != null) {
            if (cursorEditBiodata.moveToFirst()) {
                editText[0] = cursorEditBiodata.getString(1); //nama
                editText[1] = cursorEditBiodata.getString(2); //jenisKelamin
                editText[2] = cursorEditBiodata.getString(3); //tanggalLahir
                editText[3] = cursorEditBiodata.getString(4); //beratBadan
                editText[4] = cursorEditBiodata.getString(5); //tinggiBadan
                editText[5] = cursorEditBiodata.getString(6); //levelAktivitas
            }
        } else {
            editText[0] = "belom ada isinya";
            editText[1] = "belom ada isinya";
            editText[2] = "belom ada isinya";
            editText[3] = "belom ada isinya";
            editText[4] = "belom ada isinya";
            editText[5] = "belom ada isinya";
        }

        GregorianCalendar cal = new GregorianCalendar();
        int tahun_sekarang, bulan_sekarang, tanggal_sekarang, umur;
        String tgl = editText[2];
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

        String tampil_umur = String.valueOf(umur);

        ArrayList<EntryBiodata> listBiodata = new ArrayList<EntryBiodata>();
        listBiodata.add(new EntryBiodata("Nama", editText[0]));
        listBiodata.add(new EntryBiodata("Jenis Kelamin", editText[1]));
        listBiodata.add(new EntryBiodata("Tanggal Lahir", editText[2]));
        listBiodata.add(new EntryBiodata("Umur", tampil_umur));
        listBiodata.add(new EntryBiodata("Tinggi Badan", editText[4] + " cm"));
        listBiodata.add(new EntryBiodata("Berat Badan", editText[3] + " kg"));
        listBiodata.add(new EntryBiodata("Level Aktivitas", editText[5]));

        recyclerViewBiodata = getActivity().findViewById(R.id.recyclerViewBiodataAct);
        CustomAdapter mAdapter = new CustomAdapter(this.getActivity(), listBiodata);
        recyclerViewBiodata.setAdapter(mAdapter);
        recyclerViewBiodata.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}