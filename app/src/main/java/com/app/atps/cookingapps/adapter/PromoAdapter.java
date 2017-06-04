package com.app.atps.cookingapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.model.Promo;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoHolder>{
    private List<Promo> listPromo;
    private PromoAdapterCallback mCallback;
    FirebaseStorage storage;
    StorageReference storageReference ;
    private Context mContext;
    String uri_;
    @Inject
    public PromoAdapter(Context context) {
        this.mContext = context;
        listPromo = new ArrayList<>();
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReferenceFromUrl("gs://cookingapps-f8622.appspot.com");
    }
    public void setPromo(List<Promo> listPromo) {
        this.listPromo.addAll(listPromo);
    }
    @Override
    public PromoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_promo,
                parent, false);
        return new PromoHolder(view);
    }

    @Override
    public void onBindViewHolder(PromoHolder holder, int position) {
        Promo promo = listPromo.get(position);
        holder.setPromo(promo);
        holder.namaPromo_.setText(promo.getPromo());
        holder.detailPromo.setText(promo.getPromoDetail());
        Log.d("url : ",promo.getImagePromo());
        if (!promo.getImagePromo().equals("")) {
            Picasso.with(mContext)
                    .load(promo.getImagePromo())
                    .into(holder.imagePromo);
        }

    }
    private void simpleDateFormat(String date){

    }
    public void setCallback(PromoAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return listPromo.size();
    }

    class PromoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.namaPromo)
        TextView namaPromo_;
        @BindView(R.id.detailPromo)
        TextView detailPromo;
        @BindView(R.id.imagePromo)
        ImageView imagePromo;

        Promo Promo;
        public PromoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setPromo(Promo Promo) {
            this.Promo = Promo;
        }
        @OnClick(R.id.row_layout)
        void onItemClicked(View view) {
            if (mCallback != null) mCallback.onPromoClicked(Promo);
        }
    }

    public static interface PromoAdapterCallback {
        public void onPromoClicked(Promo Promo);
    }
}
