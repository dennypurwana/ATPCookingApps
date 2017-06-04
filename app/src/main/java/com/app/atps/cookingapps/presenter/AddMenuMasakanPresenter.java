package com.app.atps.cookingapps.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.view.AddMenuMasakanActivityInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by emerio on 5/27/17.
 */

public class AddMenuMasakanPresenter implements AddMenuMasakanActivityInterface.Presenter {
    private final AddMenuMasakanActivityInterface.View mView;
    private final Context mContext;
    FirebaseDatabase firebase;
    DatabaseReference menuRef;
    FirebaseStorage storage;
    StorageReference storageReference ;
    Date date = Calendar.getInstance().getTime();
    DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm ");
    String today = formatter.format(date);
     String uriImage="";
    @Inject
    public AddMenuMasakanPresenter(FirebaseDatabase firebase,AddMenuMasakanActivityInterface.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        this.firebase = firebase;
        menuRef = this.firebase.getReference("menuMasakan");
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReferenceFromUrl("gs://cookingapps-f8622.appspot.com");
    }
    @Override
    public void tambahMenuMasakan(final MenuMakanan menuMakanan, final File file, final String filename, final String status) {
        mView.showProgressBar();
        //submitImage(file,filename,id);
        StorageReference profileFileRef = storageReference.child("image_menu/"+filename+".jpg");
        Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
        try {
            ExifInterface exif = new ExifInterface(file.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            }
            else if (orientation == 3) {
                matrix.postRotate(180);
            }
            else if (orientation == 8) {
                matrix.postRotate(270);
            }
            // rotating bitmap
            bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        }
        catch (Exception e) {

        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        UploadTask uploadTask = profileFileRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child("image_menu/"+filename+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {
                        Log.d("uri",uri.toString());
                        uriImage=uri.toString();
                        if (status.equalsIgnoreCase("NEW")) {
                            final String id = menuRef.push().getKey();
                            DatabaseReference childRef = menuRef.child(id);
                            menuMakanan.setIdMenuMakanan(id);
                            menuMakanan.setImageMenu(uriImage);
                            childRef.setValue(menuMakanan);
                            childRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    mView.hideProgressBar();
                                    mView.toListMenuMasakan();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    mView.hideProgressBar();
                                    mView.resultAddMenu(false);
                                }
                            });
                        }else{
                            DatabaseReference childRef = menuRef.child(menuMakanan.getIdMenuMakanan());
                            menuMakanan.setImageMenu(uriImage);
                            childRef.setValue(menuMakanan);
                            childRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    mView.hideProgressBar();
                                    mView.toListMenuMasakan();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    mView.hideProgressBar();
                                    mView.resultAddMenu(false);
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                });
            }
        });


    }
    public void submitImage(File file, final String child) {

    }
    public void getImageProfile(String path, final String id){
        String filePath="image_menu/"+path+".jpg";

    }
}
