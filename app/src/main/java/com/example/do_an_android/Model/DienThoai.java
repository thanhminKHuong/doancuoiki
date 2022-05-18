package com.example.do_an_android.Model;

import java.io.Serializable;
import java.util.List;

public class DienThoai implements Serializable {

    public int idproduct;
    public String nameproduct;
    public int price;
    public String manhinh;
    public String hdh;
    public String camerasau;
    public String cameratruoc;
    public String chip;
    public String ram;
    public String bnt;
    public String sim;
    public String pinsac;
    public int sum;
    public int idtype;
    public String hinh;



    public String getHinh() {
        return hinh;
    }

    public DienThoai() {

    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getManhinh() {
        return manhinh;
    }

    public void setManhinh(String manhinh) {
        this.manhinh = manhinh;
    }

    public String getHdh() {
        return hdh;
    }

    public void setHdh(String hdh) {
        this.hdh = hdh;
    }

    public String getCamerasau() {
        return camerasau;
    }

    public void setCamerasau(String camerasau) {
        this.camerasau = camerasau;
    }

    public String getCameratruoc() {
        return cameratruoc;
    }

    public void setCameratruoc(String cameratruoc) {
        this.cameratruoc = cameratruoc;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getBnt() {
        return bnt;
    }

    public void setBnt(String bnt) {
        this.bnt = bnt;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getPinsac() {
        return pinsac;
    }

    public void setPinsac(String pinsac) {
        this.pinsac = pinsac;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public DienThoai(int idproduct, String nameproduct, int price, String manhinh, String hdh, String camerasau, String cameratruoc, String chip, String ram, String bnt, String sim, String pinsac, int sum, int idtype, String hinh) {
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.price = price;
        this.manhinh = manhinh;
        this.hdh = hdh;
        this.camerasau = camerasau;
        this.cameratruoc = cameratruoc;
        this.chip = chip;
        this.ram = ram;
        this.bnt = bnt;
        this.sim = sim;
        this.pinsac = pinsac;
        this.sum = sum;
        this.idtype = idtype;
        this.hinh  = hinh ;


    }

//    public DienThoai(List<HinhAnhModel> hinhAnhModelList) {
//        this.hinhAnhModelList = hinhAnhModelList;
//    }
}