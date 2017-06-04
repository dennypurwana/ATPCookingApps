package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.view.ReportListAdminActivityInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by emerio on 6/1/17.
 */

public class ReportListAdminPresenter implements ReportListAdminActivityInterface.Presenter {


    FirebaseDatabase firebase;
    DatabaseReference orderRef;

    DatabaseReference mejaRef;
    private final ReportListAdminActivityInterface.View mView;
    private final Context mContext;
    List<Order> orderList;
    List<Meja> mejaList;
    int hargaAwal=0,temp=0;
    @Inject
    public ReportListAdminPresenter(FirebaseDatabase firebase,ReportListAdminActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.firebase=firebase;
        this.mContext = mContext;
        orderRef = this.firebase.getReference("order");
        mejaRef = this.firebase.getReference("tableResto");
        orderList=new ArrayList<>();
        mejaList=new ArrayList<>();
    }


    @Override
    public void getAllOrder() {
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
                    for (int i = 0; i < orderList.size(); i++) {
                        if (i==0){
                            temp=0;
                        }
                        hargaAwal=temp+Integer.parseInt(orderList.get(i).getTotalBayarOrder());
                        temp=hargaAwal;
                    }
                    mView.showTotalBayar(String.valueOf(temp));
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
    public void getAllMeja() {
        mView.showProgress(true);
        mejaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data order : ", dataSnapshot.toString());
                Meja meja=null;
                mejaList.clear();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    meja = singleSnapshot.getValue(Meja.class);
                    mejaList.add(meja);
                }
                if (orderList.size()>0) {

                    mView.showProgress(false);
                    mView.showMejaList(mejaList);
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
    public void getOrderByMeja(final String meja) {
        Toast.makeText(mContext,"masuk",Toast.LENGTH_LONG).show();
        mView.showProgress(true);
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data order : ", dataSnapshot.toString());
                Order order=null;
                orderList.clear();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    order = singleSnapshot.getValue(Order.class);
                    if (order.getNamaMeja().equalsIgnoreCase(meja)){
                        orderList.add(order);
                    }

                }
                if (orderList.size()>0) {
                    for (int i = 0; i < orderList.size(); i++) {
                        if (i==0){
                            temp=0;
                        }
                        hargaAwal=temp+Integer.parseInt(orderList.get(i).getTotalBayarOrder());
                        temp=hargaAwal;
                    }
                    mView.showTotalBayar(String.valueOf(temp));
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
    public void getOrderByDate(String date) {

    }

    @Override
    public void getOrder() {

    }
}
