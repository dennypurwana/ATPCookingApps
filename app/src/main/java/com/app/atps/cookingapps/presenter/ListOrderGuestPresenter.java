package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.view.ListOrderGuestActivityInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by emerio on 5/29/17.
 */

public class ListOrderGuestPresenter implements ListOrderGuestActivityInterface.Presenter {
    private final ListOrderGuestActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference orderRef;

    List<Order> orderList;

    @Inject
    public ListOrderGuestPresenter(FirebaseDatabase firebase,ListOrderGuestActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase=firebase;
        orderRef = this.firebase.getReference("order");
        orderList=new ArrayList<>();
    }


    @Override
    public void getOrderList() {
        mView.showProgress(true);
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data order : ", dataSnapshot.toString());
                Order order=null;
                orderList.clear();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    order = singleSnapshot.getValue(Order.class);
                    orderList.add(order);
                }
                if (orderList.size()>0) {
                    mView.showProgress(false);
                    mView.showOrderList(orderList);


                }else {
                    mView.showProgress(false);
                    mView.showEmptyMessage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getOrderListByStatus(String status) {
        mView.showProgress(true);
        Query query = orderRef.orderByChild("statusOrder").equalTo(status);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data order : ", dataSnapshot.toString());
                Order order=null;
                final ArrayList<Order> arrayOrder;
                arrayOrder=new ArrayList<>();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    order = singleSnapshot.getValue(Order.class);
                    arrayOrder.add(order);
                }

                if (arrayOrder.size()>0) {
                    mView.showProgress(false);
                    mView.showOrderList(arrayOrder);

                }else {
                    mView.showProgress(false);
                    mView.showEmptyMessage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
