package com.app.atps.cookingapps.view;

/**
 * Created by emerio on 5/29/17.
 */

public interface DetailOrderActivityInterface {
interface Presenter{
     void updateStatusOrder(String idOrder,String status);
}
interface View{
    void showProgressBar();
    void hideProgressBar();
    void updateToCooking();
    void updateToCompleted();
    void toMenu();
    void addOrder();
    void updateToDone();

}
}
