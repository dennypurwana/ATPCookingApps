package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.Order;

/**
 * Created by emerio on 5/28/17.
 */

public interface MyOrderActivityInterface {

    interface View{
        void resultOrder(boolean status);
        void submit();
        void showProgressBar();
        void hideProgressBar();
        void toDetailOrder();
    }
    interface Presenter{
       void order(Order order,String status);
    }
}
