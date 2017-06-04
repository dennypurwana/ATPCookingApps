package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.MenuOrderMakananAdapter;
import com.app.atps.cookingapps.adapter.MyOrderAdapter;
import com.app.atps.cookingapps.data.component.DaggerMyOrderActivityComponent;
import com.app.atps.cookingapps.data.module.MyOrderActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.presenter.MyOrderPresenter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/28/17.
 */

public class MyOrderActivity extends AppCompatActivity implements MyOrderActivityInterface.View,MyOrderAdapter.MenuMakananAdapterCallback {
    @Inject
    MyOrderAdapter adapter;
    @Inject
    MyOrderPresenter myOrderPresenter;
    ArrayList<MenuMakanan> myList;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.edTextNamaPemesan)
    EditText pemesan;
    @BindView(R.id.edTextMeja)
    EditText namaMeja;
    @BindView(R.id.totalHargaOrder)
    TextView totalHarga;
    Date date = Calendar.getInstance().getTime();
    DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");
    String today = formatter.format(date);
    int hargaAwal=0,temp=0;
    String flag;
    String idOrder,namaPemesan,namaMeja_;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_layout);
        ButterKnife.bind(this);
        DaggerMyOrderActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .myOrderActivityModule(new MyOrderActivityModule(this,this))
                .build().inject(this);
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        myList=new ArrayList<>();
        myList = (ArrayList<MenuMakanan>) getIntent().getSerializableExtra("orderList");
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setMenuMakanan(myList);
        adapter.notifyDataSetChanged();
        for (int i = 0; i < myList.size(); i++) {
            if (i==0){
                temp=0;
            }
            hargaAwal=temp+Integer.parseInt(myList.get(i).getHarga());
            temp=hargaAwal;
        }
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String currency_=String.valueOf(currency.format(hargaAwal));
        totalHarga.setText("Rp."+currency_.replace("$",""));
        Intent i=getIntent();
        flag=i.getStringExtra("flag");
        if (flag.equalsIgnoreCase("UPDATE")){
            pemesan.setText(i.getStringExtra("namaPemesan"));
            pemesan.setEnabled(false);
            pemesan.setTextColor(Color.parseColor("#000000"));
            namaMeja.setText(i.getStringExtra("namaMeja"));
            namaMeja.setEnabled(false);
            namaMeja.setTextColor(Color.parseColor("#000000"));
            idOrder=i.getStringExtra("idOrder");
            namaPemesan=i.getStringExtra("namaPemesan");
            namaMeja_=i.getStringExtra("namaMeja");
        }

    }

    @Override
    public void onMenuMakananClicked(MenuMakanan menu) {

    }

    @Override
    public void onAddMenu(String totalOrder) {

    }

    @Override
    public void resultOrder(boolean status) {

    }

    @Override
    @OnClick(R.id.btnOrderMenu)
    public void submit() {
        if (pemesan.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"nama pemesan tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else  if(namaMeja.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"meja tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else {
            if (flag.equalsIgnoreCase("UPDATE")){
                Order order = new Order();
                order.setIdOrder(idOrder);
                order.setIdMeja("");
                order.setTotalBayarOrder(String.valueOf(hargaAwal));
                order.setNamaMeja(namaMeja.getText().toString());
                order.setNamaTamu(pemesan.getText().toString());
                order.setWaktuOrder(today);
                order.setStatusOrder("NEW");
                order.setArrayListMenuOrder(myList);
                myOrderPresenter.order(order,"UPDATE");
            }else {
                Order order = new Order();
                order.setIdOrder("");
                order.setIdMeja("");
                order.setTotalBayarOrder(String.valueOf(hargaAwal));
                order.setNamaMeja(namaMeja.getText().toString());
                order.setNamaTamu(pemesan.getText().toString());
                order.setWaktuOrder(today);
                order.setStatusOrder("NEW");
                order.setArrayListMenuOrder(myList);
                myOrderPresenter.order(order,"NEW");
            }
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toDetailOrder() {
        Intent intent=new Intent(MyOrderActivity.this,HomeGuestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
