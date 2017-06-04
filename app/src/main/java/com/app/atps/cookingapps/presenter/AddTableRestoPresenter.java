package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.User;
import com.app.atps.cookingapps.view.AddTableRestoActivityInterface;
import com.app.atps.cookingapps.view.AddUserByAdminActivityInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by emerio on 5/27/17.
 */

public class AddTableRestoPresenter implements AddTableRestoActivityInterface.Presenter {
    private final AddTableRestoActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference tableRestoRef;
    Date date = Calendar.getInstance().getTime();
    DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm ");
    String today = formatter.format(date);
    @Inject
    public AddTableRestoPresenter(FirebaseDatabase firebase, AddTableRestoActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        tableRestoRef = this.firebase.getReference("tableResto");
    }

    @Override
    public void tambahMeja(Meja meja, String status) {
        mView.showProgressBar();
        String id=tableRestoRef.push().getKey();
        DatabaseReference childRef = tableRestoRef.child(id);
        meja.setIdMeja(id);
        childRef.setValue(meja);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mView.hideProgressBar();
                mView.resultAddMeja(true);
                mView.toMejaList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mView.hideProgressBar();
                mView.resultAddMeja(false);
            }
        });
    }
}
