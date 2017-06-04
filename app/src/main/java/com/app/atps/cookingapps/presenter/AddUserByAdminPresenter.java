package com.app.atps.cookingapps.presenter;

import android.content.Context;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.User;
import com.app.atps.cookingapps.view.AddMenuMasakanActivityInterface;
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

public class AddUserByAdminPresenter implements AddUserByAdminActivityInterface.Presenter {
    private final AddUserByAdminActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference userRef;
    Date date = Calendar.getInstance().getTime();
    DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm ");
    String today = formatter.format(date);
    @Inject
    public AddUserByAdminPresenter(FirebaseDatabase firebase, AddUserByAdminActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        userRef = this.firebase.getReference("user");
    }

    @Override
    public void tambahUser(User user, String status) {
        mView.showProgressBar();
        String id=userRef.push().getKey();
        DatabaseReference childRef = userRef.child(id);
        user.setIdUser(id);
        childRef.setValue(user);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mView.hideProgressBar();
                mView.resultAddUser(true);
                mView.toListUser();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mView.hideProgressBar();
                mView.resultAddUser(false);
            }
        });
    }
}
