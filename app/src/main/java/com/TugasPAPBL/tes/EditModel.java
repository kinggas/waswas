package com.TugasPAPBL.tes;

public class EditModel {
    int spinnerBiodataJenisKelamin, spinnerLevelAktivitas;
    private String editTextNama, editTextTanggalLahir, editTextTinggiBadan, editTextBeratBadan;

    public EditModel(String editTextNama, String editTextTanggalLahir, String editTextTinggiBadan, String editTextBeratBadan
            , String spinnerBiodataJenisKelamin, String spinnerLevelAktivitas) {
        this.editTextNama = editTextNama;
        this.editTextTanggalLahir = editTextTanggalLahir;
        this.editTextTinggiBadan = editTextTinggiBadan;
        this.editTextBeratBadan = editTextBeratBadan;
        if (spinnerBiodataJenisKelamin.equalsIgnoreCase("Pria")) {
            this.spinnerBiodataJenisKelamin = 0;
        } else if (spinnerBiodataJenisKelamin.equalsIgnoreCase("Wanita")) {
            this.spinnerBiodataJenisKelamin = 1;
        }
        if (spinnerLevelAktivitas.equalsIgnoreCase("Tidak Olahraga")) {
            this.spinnerLevelAktivitas = 0;
        } else if (spinnerLevelAktivitas.equalsIgnoreCase("Olahraga Ringan")) {
            this.spinnerLevelAktivitas = 1;
        } else if (spinnerLevelAktivitas.equalsIgnoreCase("Olahraga Sedang")) {
            this.spinnerLevelAktivitas = 2;
        } else if (spinnerLevelAktivitas.equalsIgnoreCase("Olahraga Berat")) {
            this.spinnerLevelAktivitas = 3;
        } else if (spinnerLevelAktivitas.equalsIgnoreCase("Olahraga 2x Sehari")) {
            this.spinnerLevelAktivitas = 4;
        }
    }

    public String getEditTextNama() {
        return editTextNama;
    }

    public String getEditTextTanggalLahir() {
        return editTextTanggalLahir;
    }

    public String geteditTextTinggiBadan() {
        return editTextTinggiBadan;
    }

    public String geteditTextBeratBadan() {
        return editTextBeratBadan;
    }

    public int getspinnerBiodataJenisKelamin() {
        return spinnerBiodataJenisKelamin;
    }

    public int getspinnerLevelAktivitas() {
        return spinnerLevelAktivitas;
    }
}