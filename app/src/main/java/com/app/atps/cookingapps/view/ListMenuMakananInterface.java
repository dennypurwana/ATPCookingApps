package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;

import java.util.List;

/**
 * Created by emerio on 5/25/17.
 */

public interface ListMenuMakananInterface  {
    interface  View{
        void toFormAddMenuMakanan();
        void showMenuMakanan(List<MenuMakanan> menuMakanan);
        void showMenuMakananProgress(boolean show);
        void showEmptyMessage();
    }

    interface Presenter{
        void getAllMenuMakanan();
    }
}
