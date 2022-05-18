package com.example.do_an_android.Model;

public class HinhAnhModel {
     private int id_hinhanh , id_baiviet ;
     private String ten_hinh ;

    public HinhAnhModel() {
    }

    public HinhAnhModel(int id_hinhanh, int id_baiviet, String ten_hinh) {
        this.id_hinhanh = id_hinhanh;
        this.id_baiviet = id_baiviet;
        this.ten_hinh = ten_hinh;
    }

    public int getId_hinhanh() {
        return id_hinhanh;
    }

    public void setId_hinhanh(int id_hinhanh) {
        this.id_hinhanh = id_hinhanh;
    }

    public int getId_baiviet() {
        return id_baiviet;
    }

    public void setId_baiviet(int id_baiviet) {
        this.id_baiviet = id_baiviet;
    }

    public String getTen_hinh() {
        return ten_hinh;
    }

    public void setTen_hinh(String ten_hinh) {
        this.ten_hinh = ten_hinh;
    }
}
