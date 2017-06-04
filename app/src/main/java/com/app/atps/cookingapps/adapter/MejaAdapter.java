package com.app.atps.cookingapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.model.Meja;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class MejaAdapter extends RecyclerView.Adapter<MejaAdapter.MejaHolder>{
    private List<Meja> listMeja;
    private MejaAdapterCallback mCallback;
    @Inject
    public MejaAdapter() {
        listMeja = new ArrayList<>();
    }
    public void setMeja(List<Meja> listMeja) {
        this.listMeja.addAll(listMeja);
    }
    @Override
    public MejaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_meja,
                parent, false);
        return new MejaHolder(view);
    }

    @Override
    public void onBindViewHolder(MejaHolder holder, int position) {
        Meja Meja = listMeja.get(position);
        holder.setMeja(Meja);
        holder.namaMeja_.setText(Meja.getNamaMeja());
        holder.jumlahKursi.setText("Tersedia kursi untuk "+Meja.getJumlahKursi()+" orang.");

    }
    private void simpleDateFormat(String date){

    }
    public void setCallback(MejaAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return listMeja.size();
    }

    class MejaHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.namaMeja)
        TextView namaMeja_;
        @BindView(R.id.jumlahKursi)
        TextView jumlahKursi;


        Meja Meja;
        public MejaHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setMeja(Meja Meja) {
            this.Meja = Meja;
        }
        @OnClick(R.id.row_layout)
        void onItemClicked(View view) {
            if (mCallback != null) mCallback.onMejaClicked(Meja);
        }
    }

    public static interface MejaAdapterCallback {
        public void onMejaClicked(Meja Meja);
    }
}
