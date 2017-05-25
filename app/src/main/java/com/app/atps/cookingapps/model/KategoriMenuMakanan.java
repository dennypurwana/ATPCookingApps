package com.app.atps.cookingapps.model;

/**
 * Created by emerio on 5/24/17.
 */

public class KategoriMenuMakanan {
    private String kategoriId;
    private String kategori;
    private String imageKategori;
    public String getImageKategori() {
        return imageKategori;
    }
    public void setImageKategori(String imageKategori) {
        this.imageKategori = imageKategori;
    }
    public String getKategoriId() {
        return kategoriId;
    }
    public void setKategoriId(String kategoriId) {
        this.kategoriId = kategoriId;
    }
    public String getKategori() {
        return kategori;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
