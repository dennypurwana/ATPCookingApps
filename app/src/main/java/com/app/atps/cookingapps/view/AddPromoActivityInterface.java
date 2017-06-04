package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Promo;

import java.io.File;

/**
 * Created by emerio on 5/27/17.
 */

public interface AddPromoActivityInterface {
    interface View{
        void resultAddMenu(boolean status);
        void simpan();
        void showProgressBar();
        void hideProgressBar();
        void toListMenuMasakan();
        void selectedImage();
    }
    interface Presenter{
      void tambahPromo(Promo promo, File file, String filename, String status);
    }
}
