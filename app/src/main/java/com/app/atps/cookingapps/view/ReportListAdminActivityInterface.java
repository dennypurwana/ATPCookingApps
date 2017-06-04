package com.app.atps.cookingapps.view;

import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.Order;

import java.util.List;

/**
 * Created by emerio on 6/1/17.
 */

public interface ReportListAdminActivityInterface {
    interface  View{
        void showOrderList(List<Order> orderList);
        void showMejaList(List<Meja> mejaList);
        void showProgress(boolean show);
        void showEmptyMessage();
        void searchOrder();
        void showTotalBayar(String total);
        void print();
        void exportPdf();
    }
    interface Presenter{
        void getAllOrder();
        void getAllMeja();
        void getOrderByMeja(String meja);
        void getOrderByDate(String date);
        void getOrder();
    }
}
