package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.User;
import com.app.atps.cookingapps.view.ListUserActivityInterface;
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

public class ListUsersPresenter implements ListUserActivityInterface.Presenter {
    private final ListUserActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference userRef;


    @Inject
    public ListUsersPresenter(FirebaseDatabase firebase,ListUserActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        userRef = this.firebase.getReference("user");
    }

    @Override
    public void getAllUser() {
        mView.showUserProgress(true);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("data menu makanan : ", dataSnapshot.toString());
                List<User> userList=new ArrayList<User>();
                User user=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);
                    userList.add(user);
                }
                if (userList.size()>0) {
                    mView.showUserProgress(false);
                    mView.showUser(userList);
                }else {
                    mView.showUserProgress(false);
                    mView.showEmptyMessage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
