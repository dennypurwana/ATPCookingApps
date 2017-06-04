package com.app.atps.cookingapps.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by emerio on 5/24/17.
 */

public class Order implements Serializable{
    private String namaTamu;
    private String idOrder;
    private String idMeja;
    private String namaMeja;

    public String getTotalBayarOrder() {
        return totalBayarOrder;
    }

    public void setTotalBayarOrder(String totalBayarOrder) {
        this.totalBayarOrder = totalBayarOrder;
    }

    public ArrayList<MenuMakanan> getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(ArrayList<MenuMakanan> menuOrder) {
        this.menuOrder = menuOrder;
    }

    private String waktuOrder;
    private String statusOrder;
    private String totalBayarOrder;
    private ArrayList<MenuMakanan> menuOrder;

    public ArrayList<MenuMakanan> getArrayListMenuOrder() {
        return menuOrder;
    }

    public void setArrayListMenuOrder(ArrayList<MenuMakanan> arrayListMenuOrder) {
        this.menuOrder = arrayListMenuOrder;
    }

    public String getWaktuOrder() {
        return waktuOrder;
    }

    public void setWaktuOrder(String waktuOrder) {
        this.waktuOrder = waktuOrder;
    }

    public String getNamaMeja() {
        return namaMeja;
    }

    public void setNamaMeja(String namaMeja) {
        this.namaMeja = namaMeja;
    }


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

}
