package com.app.atps.cookingapps.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerAddPromoActivityComponent;
import com.app.atps.cookingapps.data.module.AddMenuMasakanActivityModule;
import com.app.atps.cookingapps.data.module.AddPromoActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Promo;
import com.app.atps.cookingapps.presenter.AddMenuMasakanPresenter;
import com.app.atps.cookingapps.presenter.AddPromoPresenter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class AddPromoActivity extends AppCompatActivity implements AddPromoActivityInterface.View {
   //toolbar
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;


   //progress
   @BindView(R.id.progress)
   ProgressBar progressBar;
    @BindView(R.id.titleProgress)
    TextView titleProgressBar;


    //field2
    @BindView(R.id.edTextPromo)
    EditText namaPromo;
    @BindView(R.id.edTextDetailPromo)
    EditText detailPromo;
    @BindView(R.id.edTextFilePathImage)
    EditText pathNameImage;
    @BindView(R.id.imageSelected)
    ImageView imageMenuMasakan;


    //upload
    File imgFile;
    Uri uri;
    private Bitmap bitmap;
    //
    @Inject
    AddPromoPresenter addPromoPresenter;
    Date date = Calendar.getInstance().getTime();
    DateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");
    String today = formatter.format(date);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_promo_layout);
        ButterKnife.bind(this);
        DaggerAddPromoActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .addPromoActivityModule(new AddPromoActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_food);
        toolbarTitlePage.setText("Tambah Promo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    @Override
    public void resultAddMenu(boolean status) {
        if (status){
            Toast.makeText(getApplicationContext(),"data berhasil disimpan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"data gagal disimpan",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    @OnClick(R.id.btnSimpanMenuMakanan)
    public void simpan() {
        if(namaPromo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field nama promo tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        if(detailPromo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field detail promo tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }

        else
        {
            Promo promo=new Promo();
            promo.setIdPromo("");
            promo.setPromo(namaPromo.getText().toString());
            promo.setPromoDetail(detailPromo.getText().toString());
            promo.setImagePromo(today+".jpg");
            addPromoPresenter.tambahPromo(promo,imgFile,today,"NEW");
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        titleProgressBar.setVisibility(View.VISIBLE);
        titleProgressBar.setText("sedang menyimpan data..");
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        titleProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toListMenuMasakan() {
        Intent intent=new Intent(AddPromoActivity.this,ListPromoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.selectImage)
    public void selectedImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgFile = getBitmapFile(data);
            pathNameImage.setText(imgFile.getPath().toString());
            bitmap=ShrinkBitmap(imgFile.getPath().toString(),1000,1000);
            imageMenuMasakan.setImageBitmap(bitmap);
        }
    }
    Bitmap ShrinkBitmap(String file, int width, int height){
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)height);
        int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)width);
        if (heightRatio > 1 || widthRatio > 1)
        {
            if (heightRatio > widthRatio)
            {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }
        bmpFactoryOptions.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
        return bitmap;
    }
    public File getBitmapFile(Intent data) {
        isStoragePermissionGranted();
        Uri selectedImage = data.getData();
        Cursor cursor = getContentResolver().query(selectedImage, new String[] { MediaStore.Images.Media.DATA }, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        String selectedImagePath = cursor.getString(idx);
        cursor.close();
        return new File(selectedImagePath);
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }
}
