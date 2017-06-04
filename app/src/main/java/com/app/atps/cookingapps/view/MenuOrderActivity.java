package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.MenuMakananAdapter;
import com.app.atps.cookingapps.adapter.MenuOrderMakananAdapter;
import com.app.atps.cookingapps.data.component.DaggerMenuOrderActivityContent;
import com.app.atps.cookingapps.data.module.MenuOrderActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.presenter.MenuOrderPresenter;
import com.app.atps.cookingapps.presenter.MyOrderPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/28/17.
 */

public class MenuOrderActivity extends AppCompatActivity implements MenuOrderActivityInterface.View,MenuOrderMakananAdapter.MenuMakananAdapterCallback {

    @Inject
    MenuOrderMakananAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty)
    TextView mTextEmpty;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.totalBayar)
    TextView totalHarga;
    @BindView(R.id.text_category)
    TextView titleKategori;
    @Inject
    MenuOrderPresenter menuOrderPresenter;
    ArrayList<MenuMakanan> arrayList;
    ArrayList<MenuMakanan> myList;
    String idOrder,nama,namaMeja,totalBayar;
    String flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_order_masakan);
        ButterKnife.bind(this);
        DaggerMenuOrderActivityContent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .menuOrderActivityModule(new MenuOrderActivityModule(this,this))
                .build().inject(this);
        menuOrderPresenter.getAllMenuMakanan();
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        arrayList=new ArrayList<>();
        Intent i=getIntent();
        flag=i.getStringExtra("flag");
        if (flag.equalsIgnoreCase("UPDATE")){
            idOrder=i.getStringExtra("idOrder");
            nama=i.getStringExtra("namaPemesan");
            namaMeja=i.getStringExtra("namaMeja");
            totalBayar=i.getStringExtra("totalBayar");
            myList = (ArrayList<MenuMakanan>) getIntent().getSerializableExtra("menuMakanList");
        }

       }

    @Override
    public void showMenuMakanan(List<MenuMakanan> listMenuMakanan) {
        mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setMenuMakanan(listMenuMakanan);
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
    @OnClick(R.id.btnToMyOrder)
    public void order() {

            if (flag.equalsIgnoreCase("UPDATE")){
                if (myList.size()<=0){
                    Toast.makeText(getApplicationContext(),"order terlebih dahulu",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(MenuOrderActivity.this, MyOrderActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("flag", "UPDATE");
                    intent.putExtra("orderList", myList);
                    intent.putExtra("idOrder", idOrder);
                    intent.putExtra("namaMeja", namaMeja);
                    intent.putExtra("namaPemesan", nama);
                    startActivity(intent);
                }
            }else {
                if (arrayList.size()<=0){
                    Toast.makeText(getApplicationContext(),"order terlebih dahulu",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(MenuOrderActivity.this, MyOrderActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("orderList", arrayList);
                    intent.putExtra("flag", "NEW");
                    intent.putExtra("idOrder", "");
                    intent.putExtra("namaMeja", "");
                    intent.putExtra("namaPemesan", "");
                    startActivity(intent);
                }

        }

    }

    @Override
    @OnClick(R.id.btnLayoutFood)
    public void showFood() {
        titleKategori.setText("Food");
        menuOrderPresenter.getMenuMakananByKategori("food");
    }

    @Override
    @OnClick(R.id.btnLayoutDrink)
    public void showBeverage() {
        titleKategori.setText("Beverage");
        menuOrderPresenter.getMenuMakananByKategori("beverage");
    }

    @Override
    @OnClick(R.id.btnLayoutDesert)
    public void showDesert() {
        titleKategori.setText("Desert");
        menuOrderPresenter.getMenuMakananByKategori("desert");

    }

    @Override
    public void onMenuMakananClicked(MenuMakanan menu) {
        if (flag.equalsIgnoreCase("UPDATE")){
            myList.add(menu);
        }else {
            arrayList.add(menu);
        }
    }

    @Override
    public void onRemoveMakananClicked(MenuMakanan menu) {
        if (flag.equalsIgnoreCase("UPDATE")){
            myList.remove(menu);
        }else {
            arrayList.remove(menu);
        }
    }

    @Override
    public void onAddMenu(String totalOrder) {
      //totalHarga.setText("Rp."+totalOrder+",-");
    }
}
