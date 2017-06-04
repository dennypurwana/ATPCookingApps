package com.app.atps.cookingapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.model.Order;
import com.app.atps.cookingapps.model.Order;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderHolder>{
    private List<Order> listOrder_;
    private OrderAdapterCallback mCallback;
    @Inject
    public OrderListAdapter() {
        listOrder_ = new ArrayList<>();
    }
    public void setOrder(List<Order> listOrder) {
        listOrder_.clear();
        listOrder_.addAll(listOrder);
    }
    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_order,
                parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        Order Order = listOrder_.get(position);
        holder.setOrder(Order);
        holder.namaPemesan.setText(Order.getNamaTamu());
        holder.namaMeja.setText("Meja "+Order.getNamaMeja());
        holder.orderTime.setText(Order.getWaktuOrder());

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
        @BindView(R.id.namaPemesan)
        TextView namaPemesan;
        @BindView(R.id.namaMeja)
        TextView namaMeja;
        @BindView(R.id.waktuOrder)
        TextView orderTime;
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
