package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.User;

/**
 * Created by emerio on 5/27/17.
 */

public interface AddUserByAdminActivityInterface {
    interface View{
        void resultAddUser(boolean status);
        void simpan();
        void showProgressBar();
        void hideProgressBar();
        void toListUser();
    }
    interface Presenter{
        void tambahUser(User user, String status);
    }
}
