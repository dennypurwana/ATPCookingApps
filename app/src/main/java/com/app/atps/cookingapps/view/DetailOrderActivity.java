package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.MyOrderAdapter;
import com.app.atps.cookingapps.data.component.DaggerDetailOrderActivityComponent;
import com.app.atps.cookingapps.data.module.DetailOrderActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.presenter.DetailOrderPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/29/17.
 */

public class DetailOrderActivity extends AppCompatActivity implements DetailOrderActivityInterface.View,MyOrderAdapter.MenuMakananAdapterCallback {
    @Inject
    DetailOrderPresenter detailOrderPresenter;
    @Inject
    MyOrderAdapter adapter;
    @BindView(R.id.edTextNamaPemesan)
    EditText namaPemesan;
    @BindView(R.id.edTextMeja)
    EditText namaMeja;
    @BindView(R.id.edTextStatusPesanan)
    EditText statusPesanan;
    @BindView(R.id.edTextWaktuPesanan)
    EditText waktuPesan;
    ArrayList<MenuMakanan> myList;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.totalHargaOrder)
    TextView totalHarga;
    @BindView(R.id.layoutButton)
    LinearLayout layoutButton_;
    @BindView(R.id.layoutButtonAddOrder)
    LinearLayout layoutButtonAddOrder;
    @BindView(R.id.btnCooking)
            AppCompatButton _btnCooking;
    @BindView(R.id.btnComplete)
    AppCompatButton _btnComplete;
    String idOrder;
    int hargaAwal=0,temp=0;
    Order order;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_layout);
        ButterKnife.bind(this);
        DaggerDetailOrderActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .detailOrderActivityModule(new DetailOrderActivityModule(this,this))
                .build().inject(this);
        Intent intent=getIntent();
        if (intent.getStringExtra("userType").equals("CHEF")){
            layoutButton_.setVisibility(View.VISIBLE);
            if (intent.getStringExtra("statusOrder").equalsIgnoreCase("COOKING")){
                _btnCooking.setVisibility(View.GONE);
            }
        }
        else if (intent.getStringExtra("userType").equals("GUEST")){
            layoutButtonAddOrder.setVisibility(View.VISIBLE);
            if (intent.getStringExtra("statusOrder").equalsIgnoreCase("Done")){
                layoutButtonAddOrder.setVisibility(View.GONE);
            }
        }

        idOrder=intent.getStringExtra("idOrder");
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        myList=new ArrayList<>();
        myList = (ArrayList<MenuMakanan>) getIntent().getSerializableExtra("menuOrderList");
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setMenuMakanan(myList);
        adapter.notifyDataSetChanged();
        order =new Order();
        order.setTotalBayarOrder(intent.getStringExtra("totalBayarOrder"));
        order.setIdOrder(intent.getStringExtra("idOrder"));
        order.setNamaMeja(intent.getStringExtra("namaMeja"));
        order.setWaktuOrder(intent.getStringExtra("waktuOrder"));
        order.setStatusOrder(intent.getStringExtra("statusOrder"));
        order.setArrayListMenuOrder(myList);
        order.setNamaTamu(intent.getStringExtra("namaPemesan"));
        totalHarga.setText("Rp."+intent.getStringExtra("totalBayarOrder"));
        namaPemesan.setText(intent.getStringExtra("namaPemesan"));
        namaMeja.setText(intent.getStringExtra("namaMeja"));
        statusPesanan.setText(intent.getStringExtra("statusOrder"));
        waktuPesan.setText(intent.getStringExtra("waktuOrder"));
        namaPemesan.setEnabled(false);
        namaPemesan.setTextColor(Color.parseColor("#000000"));
        namaMeja.setEnabled(false);
        namaMeja.setTextColor(Color.parseColor("#000000"));
        statusPesanan.setEnabled(false);
        statusPesanan.setTextColor(Color.parseColor("#000000"));
        waktuPesan.setEnabled(false);
        waktuPesan.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    @OnClick(R.id.btnCooking)
    public void updateToCooking() {
        detailOrderPresenter.updateStatusOrder(idOrder,"COOKING");

    }

    @Override
    @OnClick(R.id.btnComplete)
    public void updateToCompleted() {
        detailOrderPresenter.updateStatusOrder(idOrder,"COMPLETED");
    }

    @Override
    public void toMenu() {
        Intent i=new Intent(DetailOrderActivity.this,HomeActivity.class);
        startActivity(i);
    }

    @Override
    @OnClick(R.id.btnAddOrder)
    public void addOrder() {
        Intent i=new Intent(DetailOrderActivity.this,MenuOrderActivity.class);
        i.putExtra("namaPemesan",order.getNamaTamu());
        i.putExtra("flag","UPDATE");
        i.putExtra("idOrder",order.getIdOrder());
        i.putExtra("namaMeja",order.getNamaMeja());
        i.putExtra("totalBayar",order.getTotalBayarOrder());
        i.putExtra("menuMakanList",order.getArrayListMenuOrder());
        startActivity(i);
    }

    @Override
    @OnClick(R.id.btnDone)
    public void updateToDone() {
        detailOrderPresenter.updateStatusOrder(idOrder,"DONE");
    }

    @Override
    public void onMenuMakananClicked(MenuMakanan menu) {

    }

    @Override
    public void onAddMenu(String totalOrder) {

    }
}
