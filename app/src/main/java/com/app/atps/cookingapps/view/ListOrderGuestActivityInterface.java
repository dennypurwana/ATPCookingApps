package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Order;

import java.util.List;

/**
 * Created by emerio on 5/29/17.
 */

public interface ListOrderGuestActivityInterface  {

    interface View{
        void showOrderList(List<Order> orderList);
        void showProgress(boolean show);
        void showEmptyMessage();
        void showNewOrder();
        void showCompletedOrder();
        void showCookingOrder();
    }
    interface Presenter{
        void getOrderList();
        void getOrderListByStatus(String status);
    }
}
