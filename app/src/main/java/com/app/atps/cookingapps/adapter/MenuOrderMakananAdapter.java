package com.app.atps.cookingapps.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class MenuOrderMakananAdapter extends RecyclerView.Adapter<MenuOrderMakananAdapter.MenuMakananHolder>{
    private List<MenuMakanan> listMenuMakanan_;
    private MenuMakananAdapterCallback mCallback;
    private Context mContext;
    FirebaseStorage storage;
    StorageReference storageReference ;
    String uri_;
    int temp;
    int tempKurang;
    @Inject
    public MenuOrderMakananAdapter(Context context) {
        this.mContext = context;
        listMenuMakanan_ = new ArrayList<>();
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReferenceFromUrl("gs://cookingapps-f8622.appspot.com");
    }
    public void setMenuMakanan(List<MenuMakanan> listMenuMakanan) {
        listMenuMakanan_.clear();
        listMenuMakanan_.addAll(listMenuMakanan);

    }
    @Override
    public MenuMakananHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_menu_makanan_order,
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
    public void onBindViewHolder(final MenuMakananHolder holder, int position) {
        final MenuMakanan menuMakanan = listMenuMakanan_.get(position);
        holder.setMenuMakanan(menuMakanan);
        holder.titleMenu.setText(menuMakanan.getNama());
        holder.tipeMenu.setText(menuMakanan.getSpesifikasi());
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String currency_=String.valueOf(currency.format(Integer.parseInt(menuMakanan.getHarga())));
        holder.hargaMenu.setText("Rp."+currency_.replace("$",""));
        Log.d("url : ",menuMakanan.getImageMenu());
        if (!menuMakanan.getImageMenu().equals("")) {
            Picasso.with(mContext)
                    .load(menuMakanan.getImageMenu())
                    .into(holder.imageMenu);
        }

        holder.keterangan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                menuMakanan.setNotes(holder.keterangan.getText().toString());

            }
        });

        holder.btnSaveMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.jumlahOrder.getText().toString().equals("0")){
                    Toast.makeText(mContext,"Tambahkan jumlah pesanan.",Toast.LENGTH_LONG).show();
                }else {
                    holder.btnCheckMakanan.setVisibility(View.VISIBLE);
                    holder.btnSaveMakanan.setVisibility(View.GONE);
                    holder.keterangan.setEnabled(false);
                    holder.tambah.setEnabled(false);
                    holder.kurangi.setEnabled(false);
                    holder.keterangan.setTextColor(Color.parseColor("#000000"));
                    int harga = menuMakanan.getTotalOrder() * Integer.parseInt(menuMakanan.getHarga());
                    NumberFormat currency = NumberFormat.getCurrencyInstance();
                    String currency_=String.valueOf(currency.format(harga));
                    holder.hargaMenu.setText("Rp."+currency_.replace("$",""));
                    menuMakanan.setHarga(String.valueOf(harga));
                    if (mCallback != null) mCallback.onMenuMakananClicked(menuMakanan);
                }
            }
        });

       /* holder.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.kurangi.setEnabled(true);
                temp=menuMakanan.getTotalOrder()+1;
                menuMakanan.setTotalOrder(temp);
                holder.jumlahOrder.setText(String.valueOf(menuMakanan.getTotalOrder()));
            }
        });
        */

        holder.tambah.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                holder.kurangi.setEnabled(true);
                temp=menuMakanan.getTotalOrder()+1;
                menuMakanan.setTotalOrder(temp);
                holder.jumlahOrder.setText(String.valueOf(menuMakanan.getTotalOrder()));
                return false;
            }
        });
       /* holder.kurangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.jumlahOrder.getText().toString().equalsIgnoreCase("0")){
                    holder.kurangi.setEnabled(false);
                }else {
                    holder.jumlahOrder.setText(String.valueOf(menuMakanan.getTotalOrder()));
                }
                tempKurang=menuMakanan.getTotalOrder()-1;
                menuMakanan.setTotalOrder(tempKurang);

            }
        });
       */

        holder.kurangi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (holder.jumlahOrder.getText().toString().equalsIgnoreCase("0")) {
                    holder.kurangi.setEnabled(false);
                } else {
                    holder.jumlahOrder.setText(String.valueOf(menuMakanan.getTotalOrder()));
                }
                tempKurang = menuMakanan.getTotalOrder() - 1;
                menuMakanan.setTotalOrder(tempKurang);
                return  false;
            }
        });


        /*
        holder.btnAddMakanan_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!menuMakanan.isChecked()){
                    holder.btnCheckMakanan.setVisibility(View.GONE);
                    holder.btnAddMakanan_.setVisibility(View.GONE);
                    holder.keterangan.setVisibility(View.VISIBLE);
                    holder.btnSaveMakanan.setVisibility(View.VISIBLE);
                    holder.layoutCountOrder.setVisibility(View.VISIBLE);
                    menuMakanan.setChecked(true);
                }


            }
        });

        holder.btnCheckMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuMakanan.isChecked()) {
                    holder.btnAddMakanan_.setVisibility(View.VISIBLE);
                    holder.btnCheckMakanan.setVisibility(View.GONE);
                    holder.keterangan.setVisibility(View.GONE);
                    holder.btnSaveMakanan.setVisibility(View.GONE);
                    holder.layoutCountOrder.setVisibility(View.GONE);
                    if (mCallback != null) mCallback.onMenuMakananClicked(menuMakanan);
                    menuMakanan.setChecked(false);
                }
            }
        });

        */
    }
    private void simpleDateFormat(String date){

    }
    public void setCallback(MenuMakananAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
          return listMenuMakanan_.size();
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
        @BindView(R.id.countOrderLayout)
        LinearLayout layoutCountOrder;
        @BindView(R.id.jumlahPesanan)
        TextView jumlahOrder;
        @BindView(R.id.addMakanan)
        ImageView btnAddMakanan_;
        @BindView(R.id.checkMakanan)
        ImageView btnCheckMakanan;
        @BindView(R.id.saveMakanan)
        ImageView btnSaveMakanan;
        @BindView(R.id.editTextCatatan)
        EditText keterangan;
        @BindView(R.id.btnTambah)
         LinearLayout tambah;
        @BindView(R.id.btnKurang)
        LinearLayout kurangi;

        int awal=0;
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
        }

        @OnClick(R.id.addMakanan)
        void add(){
            btnCheckMakanan.setVisibility(View.GONE);
            btnAddMakanan_.setVisibility(View.GONE);
            keterangan.setVisibility(View.VISIBLE);
            btnSaveMakanan.setVisibility(View.VISIBLE);
            layoutCountOrder.setVisibility(View.VISIBLE);
        }
        @OnClick(R.id.checkMakanan)
        void check(){
            btnAddMakanan_.setVisibility(View.VISIBLE);
            btnCheckMakanan.setVisibility(View.GONE);
            keterangan.setVisibility(View.GONE);
            btnSaveMakanan.setVisibility(View.GONE);
            layoutCountOrder.setVisibility(View.GONE);
            if (mCallback != null) mCallback.onMenuMakananClicked(menuMakanan);
        }


       // void tambahOrder(){
           /* int temp=Config.temp_order+1;
            Config.temp_order=temp;
            Log.d("onClick","result : "+Config.temp_order);
            jumlahOrder.setText(String.valueOf(Config.temp_order));
            int total=Integer.parseInt(jumlahOrder.getText().toString())*Integer.parseInt(menuMakanan.getHarga());
            int totalHarga=Config.temp_total_harga+total;
            Config.temp_total_harga=totalHarga;
            if (mCallback != null) mCallback.onAddMenu(String.valueOf(Config.temp_total_harga));
            */
       // }

       // void kurangiOrder(){
            /*int temp=Config.temp_order-1;
            Config.temp_order=temp;
            if (Config.temp_order==0||Config.temp_order<0){
                layoutCountOrder.setVisibility(View.GONE);
                btnAddMakanan_.setVisibility(View.VISIBLE);
                Config.temp_order=1;
                if (mCallback != null) mCallback.onAddMenu(String.valueOf("0"));

            }
            else {
                Log.d("onClick", "result : " + Config.temp_order);
                jumlahOrder.setText(String.valueOf(Config.temp_order));
                int total = Integer.parseInt(jumlahOrder.getText().toString()) * Integer.parseInt(menuMakanan.getHarga());
                if (mCallback != null) mCallback.onAddMenu(String.valueOf(total));
            }
            */
       // }

    }
    public static interface MenuMakananAdapterCallback {
        public void onMenuMakananClicked(MenuMakanan menu);
        public void onRemoveMakananClicked(MenuMakanan menu);
        public void onAddMenu(String totalOrder);
    }
}
