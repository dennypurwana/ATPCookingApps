package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;

import java.io.File;

/**
 * Created by emerio on 5/27/17.
 */

public interface AddMenuMasakanActivityInterface  {
    interface View{
        void resultAddMenu(boolean status);
        void simpan();
        void showProgressBar();
        void hideProgressBar();
        void toListMenuMasakan();
        void selectedImage();
    }
    interface Presenter{
      void tambahMenuMasakan(MenuMakanan menuMakanan, File file,String filename,String status);
    }
}
