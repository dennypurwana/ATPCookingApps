package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.adapter.OrderListAdapter;
import com.app.atps.cookingapps.adapter.ReportOrderAdapter;
import com.app.atps.cookingapps.data.component.DaggerReportListAdminComponent;
import com.app.atps.cookingapps.data.module.ReportListAdminModule;
import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.presenter.ReportListAdminPresenter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 6/1/17.
 */

public class ReportListAdminActivity extends AppCompatActivity implements ReportListAdminActivityInterface.View,ReportOrderAdapter.OrderAdapterCallback, AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_category)
    TextView mTextCategori;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.spinnerMeja)
    Spinner spinnerMeja;
    @BindView(R.id.paramSearch)
    EditText param;
    @BindView(R.id.totalOrderAll)
    TextView _totalOrderAll;
    @BindView(R.id.btnSearch)
    AppCompatButton search;
    @Inject
    ReportOrderAdapter adapter;
    ArrayList<Order> orderDetailList;
    ArrayList<String> mejaListString;
    @Inject
    ReportListAdminPresenter reportListAdminPresenter;
    String meja="";
    public String selectedMeja="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_admin_layout);
        DaggerReportListAdminComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .reportListAdminModule(new ReportListAdminModule(this,this))
                .build().inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_food);
        toolbarTitlePage.setText("Report List Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        orderDetailList=new ArrayList<>();
        mejaListString=new ArrayList<>();
        reportListAdminPresenter.getAllOrder();
        adapter.setCallback(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showOrderList(List<Order> orderList) {
        //mTextEmpty.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        adapter.setOrder(orderList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMejaList(List<Meja> mejaList) {
        for (int i = 0; i < mejaList.size(); i++) {
            mejaListString.add(mejaList.get(i).getNamaMeja());
        }

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
      //  mTextEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btnSearch)
    public void searchOrder() {
        if (param.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"input pencarian terlebih dahulu",Toast.LENGTH_LONG).show();
        }else {
            reportListAdminPresenter.getOrderByMeja(param.getText().toString());
        }
    }

    @Override
    public void showTotalBayar(String total) {
        //if ()
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String currency_=String.valueOf(currency.format(Integer.parseInt(total)));
        _totalOrderAll.setText("Rp."+currency_.replace("$",""));
    }

    @Override
    @OnClick(R.id.btnPrint)
    public void print() {
        Toast.makeText(getApplicationContext(),"function under construction",Toast.LENGTH_LONG).show();

    }

    @Override
    @OnClick(R.id.btnExportPdf)
    public void exportPdf() {
        Toast.makeText(getApplicationContext(),"function under construction",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnAll)
    public void all() {
            reportListAdminPresenter.getAllOrder();

    }

    @Override
    public void onOrderClicked(Order order) {
        Intent intent = new Intent(ReportListAdminActivity.this, DetailOrderActivity.class);
        intent.putExtra("userType", "ADMIN");
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (spinnerMeja.getSelectedItem().toString()!=null){
            mTextCategori.setText(""+spinnerMeja.getSelectedItem().toString());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

