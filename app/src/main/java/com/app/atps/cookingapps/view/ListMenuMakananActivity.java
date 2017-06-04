package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.MenuMakananAdapter;
import com.app.atps.cookingapps.data.component.DaggerListMenuMakananActivityComponent;
import com.app.atps.cookingapps.data.module.ListMenuMakananActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.presenter.ListMenuMakananPresenter;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/25/17.
 */

public class ListMenuMakananActivity extends AppCompatActivity implements ListMenuMakananInterface.View,MenuMakananAdapter.MenuMakananAdapterCallback {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    @Inject
    ListMenuMakananPresenter listMenuMakananPresenter;
    @Inject
    MenuMakananAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty)
    TextView mTextEmpty;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_menu_makanan);
        ButterKnife.bind(this);
        DaggerListMenuMakananActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .listMenuMakananActivityModule(new ListMenuMakananActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_food);
        toolbarTitlePage.setText("List Menu Masakan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listMenuMakananPresenter.getAllMenuMakanan();
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.btnToFormAddMenuMakanan)
    public void toFormAddMenuMakanan() {
        Intent i=new Intent(ListMenuMakananActivity.this,AddMenuMasakanActivity.class);
        i.putExtra("imageMakanan","");
        i.putExtra("namaMakanan","");
        i.putExtra("detailMakanan","");
        i.putExtra("harga","");
        i.putExtra("stok","");
        i.putExtra("tipe","CREATE");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void showMenuMakanan(List<MenuMakanan> menuMakanan) {
        mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setMenuMakanan(menuMakanan);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMenuMakananProgress(boolean show) {
        if (show && adapter.getItemCount() == 0) {
            mProgress.setVisibility(View.VISIBLE);
        } else {
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyMessage() {
        mTextEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onMenuMakananClicked(MenuMakanan menu) {
        Intent i=new Intent(ListMenuMakananActivity.this,DetailMenuMakananActivity.class);
        i.putExtra("id",menu.getIdMenuMakanan());
        i.putExtra("imageMakanan",menu.getImageMenu());
        i.putExtra("namaMakanan",menu.getNama());
        i.putExtra("detailMakanan",menu.getSpesifikasi());
        i.putExtra("harga",menu.getHarga());
        i.putExtra("stok",menu.getStok());
        startActivity(i);

    }
}
