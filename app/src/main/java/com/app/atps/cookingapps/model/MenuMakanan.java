package com.app.atps.cookingapps.model;

import java.io.Serializable;

/**
 * Created by emerio on 5/24/17.
 */


public class MenuMakanan implements Serializable{
    private String idMenuMakanan;
    private String nama;
    private String stok;
    private String spesifikasi;
    private String harga;
    private String idKategori;
    private String imageMenu;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private int totalOrder;
    public int getTotalOrder() {
        return totalOrder;
    }
    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    private String notes;
    public String getIdMenuMakanan() {
        return idMenuMakanan;
    }

    public void setIdMenuMakanan(String idMenuMakanan) {
        this.idMenuMakanan = idMenuMakanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi) {
        this.spesifikasi = spesifikasi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getImageMenu() {
        return imageMenu;
    }

    public void setImageMenu(String imageMenu) {
        this.imageMenu = imageMenu;
    }


}
