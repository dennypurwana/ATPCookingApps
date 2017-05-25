package com.app.atps.cookingapps.model;

/**
 * Created by emerio on 5/24/17.
 */

public class Order {
    private String namaTamu;
    private String idOrder;
    private String idMeja;
    private String idMenuMakanan;
    private String waktuOrder;
    private int jumlahOrder;
    private int totalOrder;
    private String statusOrder;

    public String getStatusOrder() {
        return statusOrder;
    }
    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
    public String getNamaTamu() {
        return namaTamu;
    }
    public void setNamaTamu(String namaTamu) {
        this.namaTamu = namaTamu;
    }
    public String getIdOrder() {
        return idOrder;
    }
    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
    public String getIdMeja() {
        return idMeja;
    }
    public void setIdMeja(String idMeja) {
        this.idMeja = idMeja;
    }
    public String getIdMenuMakanan() {
        return idMenuMakanan;
    }
    public void setIdMenuMakanan(String idMenuMakanan) {
        this.idMenuMakanan = idMenuMakanan;
    }
    public String getWaktuOrder() {
        return waktuOrder;
    }
    public void setWaktuOrder(String waktuOrder) {
        this.waktuOrder = waktuOrder;
    }
    public int getJumlahOrder() {
        return jumlahOrder;
    }
    public void setJumlahOrder(int jumlahOrder) {
        this.jumlahOrder = jumlahOrder;
    }
    public int getTotalOrder() {
        return totalOrder;
    }
    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }
}
