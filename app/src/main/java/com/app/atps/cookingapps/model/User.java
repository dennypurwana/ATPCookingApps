package com.app.atps.cookingapps.model;

/**
 * Created by emerio on 5/24/17.
 */

public class User {
    private String idUser;
    private String tipeUser;
    private String nama;
    private String password;
    private String phone;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getTipeUser() {
        return tipeUser;
    }
    public void setTipeUser(String tipeUser) {
        this.tipeUser = tipeUser;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
