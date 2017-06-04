package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.User;
import com.app.atps.cookingapps.view.LoginActivityInterface;
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
 * Created by emerio on 5/25/17.
 */

public class LoginPresenter implements LoginActivityInterface.Presenter {
    private final LoginActivityInterface.View mView;
    private  final Context context;
    FirebaseDatabase firebase;
    DatabaseReference userRef;

    @Inject
    public LoginPresenter(FirebaseDatabase firebase,LoginActivityInterface.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        this.firebase = firebase;
        userRef = this.firebase.getReference("user");
    }


    @Override
    public void getUserByEmailPassword(final String email, final String password) {
        mView.showProgress();
        Query query = userRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data user login: ", dataSnapshot.toString());
                User user=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);
                }
                if (user!=null){
                String userEmail=user.getEmail();
                String userPassword=user.getPassword();
                if (email.equals(userEmail)&&password.equals(userPassword)){
                    mView.hideProgress();
                    mView.resultLogin(true);
                }else if(userEmail.equals(email)&&!password.equals(password)){
                    mView.hideProgress();
                    mView.resultError("Password tidak sesuai");
                }else {
                    mView.hideProgress();
                    mView.resultError("User tidak terdaftar");
                }
                }else{
                    mView.hideProgress();
                    mView.resultError("User tidak terdaftar");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
