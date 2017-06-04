package com.app.atps.cookingapps.view;

/**
 * Created by emerio on 6/1/17.
 */

public interface DetailMenuMakananActivityInterface {
    interface View{
        void update();
        void delete();
        void toMakananList();
    }
    interface Presenter{
        void delete(String makananId);

    }
}
