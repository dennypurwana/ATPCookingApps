package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerDetailMenuMakananActivityComponent;
import com.app.atps.cookingapps.data.module.DetailMenuMakananActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.presenter.DetailMenuMakananPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 6/1/17.
 */

public class DetailMenuMakananActivity extends AppCompatActivity implements DetailMenuMakananActivityInterface.View {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    @BindView(R.id.imageMenuMakanan)
    ImageView imageMenu;
    @BindView(R.id.menuMakanan)
    TextView namaMakanan;
    @BindView(R.id.spesifikasiMakanan)
    TextView spesifikasiMakanan;
    @BindView(R.id.hargaMakanan)
    TextView hargaMakanan;
    @Inject
    DetailMenuMakananPresenter detailMenuMakananPresenter;

    MenuMakanan menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_makanan_list);
        ButterKnife.bind(this);
        DaggerDetailMenuMakananActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .detailMenuMakananActivityModule(new DetailMenuMakananActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_food);
        toolbarTitlePage.setText("Detail Menu Masakan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent i=getIntent();
        menu=new MenuMakanan();
        menu.setImageMenu("");
        menu.setIdMenuMakanan(i.getStringExtra("id"));
        menu.setNama(i.getStringExtra("namaMakanan"));
        menu.setSpesifikasi(i.getStringExtra("detailMakanan"));
        menu.setHarga(i.getStringExtra("harga"));
        menu.setStok(i.getStringExtra("stok"));
        namaMakanan.setText(i.getStringExtra("namaMakanan"));
        spesifikasiMakanan.setText(i.getStringExtra("detailMakanan"));
        hargaMakanan.setText("Rp."+i.getStringExtra("harga"));
        if (!i.getStringExtra("imageMakanan").equals("")) {
            Picasso.with(getApplicationContext())
                    .load(i.getStringExtra("imageMakanan"))
                    .into(imageMenu);
        }
    }

    @Override
    @OnClick(R.id.btnEdit)
    public void update() {
        Intent i=new Intent(DetailMenuMakananActivity.this,AddMenuMasakanActivity.class);
        i.putExtra("id",menu.getIdMenuMakanan());
        i.putExtra("imageMakanan",menu.getImageMenu());
        i.putExtra("namaMakanan",menu.getNama());
        i.putExtra("detailMakanan",menu.getSpesifikasi());
        i.putExtra("harga",menu.getHarga());
        i.putExtra("stok",menu.getStok());
        i.putExtra("tipe","UPDATE");
        startActivity(i);
    }

    @Override
    @OnClick(R.id.btnDelete)
    public void delete() {
        detailMenuMakananPresenter.delete(getIntent().getStringExtra("id"));
    }

    @Override
    public void toMakananList() {
        Intent i=new Intent(DetailMenuMakananActivity.this,ListMenuMakananActivity.class);
        startActivity(i);
    }
}
