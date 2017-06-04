package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Promo;

import java.util.List;

/**
 * Created by emerio on 5/25/17.
 */

public interface ListPromoActivityInterface {
    interface View{
        void toFormAddPromo();
        void showPromo(List<Promo> prmoList);
        void showProgress(boolean show);
        void showEmptyMessage();
    }
    interface Presenter{
        void getAllPromo();
    }
}
