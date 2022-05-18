package com.example.do_an_android.Model;

import android.widget.EditText;

import java.util.List;

public class BinhLuanModel {
    private int id_binhluan  , id_baiviet ;
    private String noidung , ten ;





    public BinhLuanModel() {
    }

    public BinhLuanModel(int id_binhluan, int id_baiviet, String noidung, String ten ) {
        this.id_binhluan = id_binhluan;
        this.id_baiviet = id_baiviet;
        this.noidung = noidung;
        this.ten = ten;

    }

    public int getId_binhluan() {
        return id_binhluan;
    }

    public void setId_binhluan(int id_binhluan) {
        this.id_binhluan = id_binhluan;
    }

    public int getId_baiviet() {
        return id_baiviet;
    }

    public void setId_baiviet(int id_baiviet) {
        this.id_baiviet = id_baiviet;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }


}
