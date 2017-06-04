package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.view.ListTableRestoActivity;
import com.app.atps.cookingapps.view.ListTableRestoActivityInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by emerio on 5/25/17.
 */

public class ListTableRestoPresenter implements ListTableRestoActivityInterface.Presenter {
    private final ListTableRestoActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference tableRestoRef;
    @Inject
    public ListTableRestoPresenter(FirebaseDatabase firebase,ListTableRestoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase=firebase;
        tableRestoRef = this.firebase.getReference("tableResto");
    }

    @Override
    public void getAllDataMeja() {
        mView.showMejaProgress(true);
        tableRestoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Meja> mejaList=new ArrayList<Meja>();
                Meja meja=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    meja = singleSnapshot.getValue(Meja.class);
                    mejaList.add(meja);
                }
                if (mejaList.size()>0) {
                    mView.showMejaProgress(false);
                    mView.showMeja(mejaList);
                }else {
                    mView.showMejaProgress(false);
                    mView.showEmptyMessage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
