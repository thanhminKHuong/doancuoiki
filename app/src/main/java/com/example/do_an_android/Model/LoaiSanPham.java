package com.example.do_an_android.Model;

public class LoaiSanPham {

    private int idtype;
    private String nametype;


    public LoaiSanPham(int idtype, String nametype) {
     this.nametype= nametype;
        this.idtype = idtype;


}
    public LoaiSanPham(){}

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype; }

    public String getNametype() {
        return nametype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }
}
