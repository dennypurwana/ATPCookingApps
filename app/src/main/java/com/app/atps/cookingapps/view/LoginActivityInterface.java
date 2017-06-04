package com.app.atps.cookingapps.view;

/**
 * Created by emerio on 5/25/17.
 */

public interface LoginActivityInterface {
    interface View{
        void toRegisterPage();
        void toDashboardPage();
        void submit();
        void resultLogin(boolean status);
        void resultError(String message);
        void showProgress();
        void hideProgress();
    }
    interface Presenter{
        void getUserByEmailPassword(String email,String password);
    }
}
