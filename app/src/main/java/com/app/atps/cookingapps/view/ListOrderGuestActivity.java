package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.OrderListAdapter;
import com.app.atps.cookingapps.data.component.DaggerListOrderGuestActivityComponent;
import com.app.atps.cookingapps.data.module.ListOrderGuestActivityModule;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.presenter.ListOrderGuestPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/29/17.
 */

public class ListOrderGuestActivity extends AppCompatActivity implements ListOrderGuestActivityInterface.View ,OrderListAdapter.OrderAdapterCallback{

    @Inject
    ListOrderGuestPresenter listOrderGuestPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_empty)
    TextView mTextEmpty;
    @BindView(R.id.text_category)
    TextView mTextCategori;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.btnKategori)
    LinearLayout btnKategori_;
    @BindView(R.id.btnLayoutOrderBaru)
    LinearLayout btnOrderBaru;
    @BindView(R.id.btnLayoutOrderSelesai)
    LinearLayout btnOrderSelesai;
    @Inject
    OrderListAdapter adapter;
    ArrayList<Order> orderDetailList;
    String userType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_my_order_guest);
        ButterKnife.bind(this);
        DaggerListOrderGuestActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .listOrderGuestActivityModule(new ListOrderGuestActivityModule(this,this))
                .build().inject(this);
        Intent intent =getIntent();
        userType=intent.getStringExtra("userType");
        if (userType.equals("CHEF")){
            btnKategori_.setVisibility(View.VISIBLE);
            listOrderGuestPresenter.getOrderListByStatus("NEW");
            mTextCategori.setText("order baru");
        }else if (userType.equalsIgnoreCase("GUEST")){
            listOrderGuestPresenter.getOrderList();
        }
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        orderDetailList=new ArrayList<>();
    }

    @Override
    public void showOrderList(List<Order> orderList) {
        mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setOrder(orderList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
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
    @OnClick(R.id.btnLayoutOrderBaru)
    public void showNewOrder() {
        mTextCategori.setText("order baru");
        listOrderGuestPresenter.getOrderListByStatus("NEW");
    }

    @Override
    @OnClick(R.id.btnLayoutOrderSelesai)
    public void showCompletedOrder() {
        mTextCategori.setText("order completed");
        listOrderGuestPresenter.getOrderListByStatus("COMPLETED");
    }

    @Override
    @OnClick(R.id.btnLayoutOrderCooking)
    public void showCookingOrder() {
        mTextCategori.setText("order cooking");
        listOrderGuestPresenter.getOrderListByStatus("COOKING");
    }

    @Override
    public void onOrderClicked(Order order) {
            Intent intent = new Intent(ListOrderGuestActivity.this, DetailOrderActivity.class);
            intent.putExtra("userType", userType);
            intent.putExtra("idOrder",order.getIdOrder());
            intent.putExtra("namaPemesan", order.getNamaTamu());
            intent.putExtra("namaMeja",order.getNamaMeja());
            intent.putExtra("idOrder",order.getIdOrder());
            intent.putExtra("waktuOrder",order.getWaktuOrder());
            intent.putExtra("statusOrder",order.getStatusOrder());
            intent.putExtra("totalBayarOrder",order.getTotalBayarOrder());
            intent.putExtra("menuOrderList",order.getArrayListMenuOrder());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

    }
}
