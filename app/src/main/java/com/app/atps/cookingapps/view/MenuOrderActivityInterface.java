package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;

import java.util.List;

/**
 * Created by emerio on 5/28/17.
 */

public interface MenuOrderActivityInterface {
    interface View{
        void showMenuMakanan(List<MenuMakanan> listMenuMakanan);
        void showMenuMakananProgress(boolean show);
        void showEmptyMessage();
        void order();
        void showFood();
        void showBeverage();
        void showDesert();
    }
    interface Presenter{
        void getAllMenuMakanan();
        void getMenuMakananByKategori(String kategoriId);

    }
}
