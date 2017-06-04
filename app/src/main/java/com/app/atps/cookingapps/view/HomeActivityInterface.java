package com.app.atps.cookingapps.view;

/**
 * Created by emerio on 5/25/17.
 */

public interface HomeActivityInterface {
    interface View{
        void launcher();
        void toWaitersPage();
        void toAdminPage();
        void toChefPage();
        void toGuestPage();
    }
    interface Presenter{
    }
}
