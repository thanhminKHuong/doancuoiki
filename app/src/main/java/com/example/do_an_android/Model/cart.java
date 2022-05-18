package com.example.do_an_android.Model;

public class cart {
    int idcart;
    String nameproduct;
    int price;
    int sum;
    int totalprice;

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    String hinh;


    public cart(int idcart, String nameproduct, int price, int sum, int totalprice, String hinh) {
        this.idcart = idcart;
        this.nameproduct = nameproduct;
        this.price = price;
        this.sum = sum;
        this.totalprice = totalprice;
        this.hinh = hinh;
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public cart() {
    }
}