package com.app.atps.cookingapps.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.util.Config;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MenuMakananHolder>{
    private ArrayList<MenuMakanan> listMenuMakanan;
    private MenuMakananAdapterCallback mCallback;
    private Context mContext;
    FirebaseStorage storage;
    StorageReference storageReference ;
    String uri_;
    @Inject
    public MyOrderAdapter(Context context) {
        this.mContext = context;
        listMenuMakanan = new ArrayList<>();
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReferenceFromUrl("gs://cookingapps-f8622.appspot.com");
    }
    public void setMenuMakanan(ArrayList<MenuMakanan> listMenuMakanan) {
        this.listMenuMakanan.addAll(listMenuMakanan);
    }
    @Override
    public MenuMakananHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_my_order,
                parent, false);
        return new MenuMakananHolder(view);
    }
    public void getImageProfile(String path){
        String filePath="image_menu/"+path+".jpg";
        storageReference.child(filePath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("uri",uri.toString());
                uri_=uri.toString();
               // view.showImage(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
    @Override
    public void onBindViewHolder(MenuMakananHolder holder, int position) {
        MenuMakanan menuMakanan = listMenuMakanan.get(position);
        holder.setMenuMakanan(menuMakanan);
        holder.titleMenu.setText(menuMakanan.getNama());
        holder.tipeMenu.setText(menuMakanan.getSpesifikasi()); NumberFormat currency = NumberFormat.getCurrencyInstance();
        String currency_=String.valueOf(currency.format(Integer.parseInt(menuMakanan.getHarga())));
        holder.hargaMenu.setText("Rp."+currency_.replace("$",""));
        Log.d("url : ",menuMakanan.getImageMenu());
        if (!menuMakanan.getImageMenu().equals("")) {
            Picasso.with(mContext)
                    .load(menuMakanan.getImageMenu())
                    .into(holder.imageMenu);
        }
        holder.keterangan.setText(menuMakanan.getNotes());
        holder.totalOrder.setText("Jumlah Order "+menuMakanan.getTotalOrder());

    }
    private void simpleDateFormat(String date){

    }
    public void setCallback(MenuMakananAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return listMenuMakanan.size();
    }

    class MenuMakananHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageMenuMakanan)
        ImageView imageMenu;
        @BindView(R.id.titleMenuMakanan)
        TextView titleMenu;
        @BindView(R.id.tipeMenuMakanan)
        TextView tipeMenu;
        @BindView(R.id.hargaMenuMakanan)
        TextView hargaMenu;
        @BindView(R.id.keteranganOrder)
        TextView keterangan;
        @BindView(R.id.totalOrder)
        TextView totalOrder;
        Context context;
        MenuMakanan menuMakanan;
        public MenuMakananHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setMenuMakanan(MenuMakanan menu) {
            this.menuMakanan = menu;
        }
        @OnClick(R.id.row_layout)
        void onItemClicked(View view) {
            if (mCallback != null) mCallback.onMenuMakananClicked(menuMakanan);
        }


    }
    public static interface MenuMakananAdapterCallback {
        public void onMenuMakananClicked(MenuMakanan menu);
        public void onAddMenu(String totalOrder);
    }
}
