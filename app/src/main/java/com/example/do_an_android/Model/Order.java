package com.example.do_an_android.Model;

import java.util.Date;

public class Order {

    private int idorder;
    private String status;
    private Date date;
    private String address;
    private int iduser;

    public Order(int idorder, String status, Date date, String address, int iduser) {
        this.idorder = idorder;
        this.status = status;
        this.date =  date;
        this.address = address;
        this.iduser = iduser;

    }
 public Order()
 {}
    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
