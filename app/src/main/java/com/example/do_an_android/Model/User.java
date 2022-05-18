package com.example.do_an_android.Model;

import java.util.Date;

public class User
{
    public int iduser;
    public String nameuser;
    public String birthday;
    public String phone;
    public String email;
    public  String gender;
    public String password;
    public int typeuser;

    public User() {
    }

    public User(int iduser, String nameuser, String birthday, String phone, String email, String gender, String password, int typeuser) {
        this.iduser = iduser;
        this.nameuser = nameuser;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.typeuser = typeuser;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(int typeuser) {
        this.typeuser = typeuser;
    }
}
