package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.User;

/**
 * Created by emerio on 5/27/17.
 */

public interface AddTableRestoActivityInterface {
    interface View{
        void resultAddMeja(boolean status);
        void simpan();
        void showProgressBar();
        void hideProgressBar();
        void toMejaList();
    }
    interface Presenter{
        void tambahMeja(Meja meja, String status);
    }
}
