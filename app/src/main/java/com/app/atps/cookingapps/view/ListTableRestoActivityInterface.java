package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.User;

import java.util.List;

/**
 * Created by emerio on 5/25/17.
 */

public interface ListTableRestoActivityInterface {
    interface View{
        void toAddMeja();
        void showMeja(List<Meja> mejaList);
        void showMejaProgress(boolean show);
        void showEmptyMessage();
    }
    interface Presenter{
        void getAllDataMeja();
    }

}
