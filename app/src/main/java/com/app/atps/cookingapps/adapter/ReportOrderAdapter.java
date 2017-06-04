package com.app.atps.cookingapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.model.Order;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class ReportOrderAdapter extends RecyclerView.Adapter<ReportOrderAdapter.OrderHolder>{
    private List<Order> listOrder_;
    private OrderAdapterCallback mCallback;
    @Inject
    public ReportOrderAdapter() {
        listOrder_ = new ArrayList<>();
    }
    public void setOrder(List<Order> listOrder) {
        listOrder_.clear();
        listOrder_.addAll(listOrder);
    }
    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_order_report,
                parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        Order Order = listOrder_.get(position);
        int urut=(position+1);
        holder.setOrder(Order);
        holder.no.setText(String.valueOf(urut));
        holder.namaPemesan.setText(Order.getNamaTamu());
        holder.namaMeja.setText(Order.getNamaMeja());
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String currency_=String.valueOf(currency.format(Integer.parseInt(Order.getTotalBayarOrder())));
        holder.totalOrder.setText("Rp."+currency_.replace("$",""));

    }

    private void formatCurrency(int input){
        Locale dutch = new Locale("nl", "NL");
        NumberFormat numberFormatDutch = NumberFormat.getCurrencyInstance(dutch);
        String c = numberFormatDutch.format(new BigDecimal(input));
        System.out.println("Currency Format: "+ c);
        try {
            Number  d = numberFormatDutch.parse(c);
            BigDecimal bd = new BigDecimal(d.toString());
            System.out.println(bd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void simpleDateFormat(String date){

    }
    public void setCallback(OrderAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return listOrder_.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.no)
        TextView no;
        @BindView(R.id.namaPemesan)
        TextView namaPemesan;
        @BindView(R.id.namaMeja)
        TextView namaMeja;
        @BindView(R.id.totalOrder)
        TextView totalOrder;
        Order Order;
        public OrderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        public void setOrder(Order Order) {
            this.Order = Order;
        }
        @OnClick(R.id.row_layout)
        void onItemClicked(View view) {
            if (mCallback != null) mCallback.onOrderClicked(Order);
        }
    }
    public static interface OrderAdapterCallback {
        public void onOrderClicked(Order Order);
    }
}
