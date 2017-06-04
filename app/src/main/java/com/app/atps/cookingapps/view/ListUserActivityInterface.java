package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.User;

import java.util.List;

/**
 * Created by emerio on 5/25/17.
 */

public interface ListUserActivityInterface {
    interface  View{
      void toAddUser();
        void showUser(List<User> userList);
        void showUserProgress(boolean show);
        void showEmptyMessage();
    }
    interface Presenter{
      void getAllUser();
    }
}
