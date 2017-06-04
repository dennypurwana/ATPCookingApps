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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerAddMenuMasakanActivityComponent;
import com.app.atps.cookingapps.data.module.AddMenuMasakanActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.presenter.AddMenuMasakanPresenter;

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

public class AddMenuMasakanActivity extends AppCompatActivity implements AddMenuMasakanActivityInterface.View {
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
    @BindView(R.id.edTextNamaMenu)
    EditText namaMenuMakanan;
    @BindView(R.id.edTextDetailMenu)
    EditText detailMenuMakanan;
    @BindView(R.id.spinnerKategori)
    Spinner kategoriMenuMakanan;
    @BindView(R.id.edTextStok)
    EditText jumlahMenuMakanan;
    @BindView(R.id.edTextHarga)
    EditText hargaMenuMakanan;
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
    AddMenuMasakanPresenter addMenuMasakanPresenter;
    Date date = Calendar.getInstance().getTime();
    DateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");
    String today = formatter.format(date);
    String tipe;
    String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_tambah_menu_masakan);
        ButterKnife.bind(this);
        DaggerAddMenuMasakanActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .addMenuMasakanActivityModule(new AddMenuMasakanActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_food);
        toolbarTitlePage.setText("Tambah Menu Masakan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent i=getIntent();
        tipe=i.getStringExtra("tipe");
        id=i.getStringExtra("id");
        if (tipe.equalsIgnoreCase("UPDATE")){
            Toast.makeText(getApplicationContext(),""+i.getStringExtra("tipe"),Toast.LENGTH_LONG).show();
            namaMenuMakanan.setText(i.getStringExtra("namaMakanan"));
            detailMenuMakanan.setText(i.getStringExtra("detailMakanan"));
            hargaMenuMakanan.setText(i.getStringExtra("harga"));
            jumlahMenuMakanan.setText(i.getStringExtra("stok"));
        }



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
        if(namaMenuMakanan.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field nama masakan tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
       else if(pathNameImage.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field image tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        if(kategoriMenuMakanan.getSelectedItem().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field kategori masakan tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        {
            if (tipe.equalsIgnoreCase("UPDATE")){
                MenuMakanan menu = new MenuMakanan();
                menu.setIdMenuMakanan(id);
                menu.setNama(namaMenuMakanan.getText().toString());
                menu.setChecked(false);
                menu.setSpesifikasi(detailMenuMakanan.getText().toString());
                menu.setStok(jumlahMenuMakanan.getText().toString());
                menu.setHarga(hargaMenuMakanan.getText().toString());
                menu.setImageMenu(pathNameImage.getText().toString());
                menu.setIdKategori(kategoriMenuMakanan.getSelectedItem().toString());
                menu.setTotalOrder(0);
                menu.setImageMenu(today + ".jpg");
                addMenuMasakanPresenter.tambahMenuMasakan(menu, imgFile, today, "UPDATE");
            }else {
                MenuMakanan menu = new MenuMakanan();
                menu.setIdMenuMakanan("");
                menu.setNama(namaMenuMakanan.getText().toString());
                menu.setChecked(false);
                menu.setSpesifikasi(detailMenuMakanan.getText().toString());
                menu.setStok(jumlahMenuMakanan.getText().toString());
                menu.setHarga(hargaMenuMakanan.getText().toString());
                menu.setImageMenu(pathNameImage.getText().toString());
                menu.setIdKategori(kategoriMenuMakanan.getSelectedItem().toString());
                menu.setTotalOrder(0);
                menu.setImageMenu(today + ".jpg");
                addMenuMasakanPresenter.tambahMenuMasakan(menu, imgFile, today, "NEW");
            }
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
        Intent intent=new Intent(AddMenuMasakanActivity.this,ListMenuMakananActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    @OnClick(R.id.selectImage)
    public void selectedImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
        Cursor cursor = getContentResolver().query(selectedImage, new String[] { android.provider.MediaStore.Images.Media.DATA }, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        String selectedImagePath = cursor.getString(idx);
        cursor.close();
        return new File(selectedImagePath);
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
