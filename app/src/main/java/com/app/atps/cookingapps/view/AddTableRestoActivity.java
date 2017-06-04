package com.app.atps.cookingapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.atps.cookingapps.App;
import com.app.atps.cookingapps.R;
import com.app.atps.cookingapps.data.component.DaggerAddTableRestoActivityComponent;
import com.app.atps.cookingapps.data.module.AddTableRestoActivityModule;
import com.app.atps.cookingapps.model.Meja;
import com.app.atps.cookingapps.model.MenuMakanan;
import com.app.atps.cookingapps.presenter.AddMenuMasakanPresenter;
import com.app.atps.cookingapps.presenter.AddTableRestoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emerio on 5/27/17.
 */

public class AddTableRestoActivity extends AppCompatActivity implements AddTableRestoActivityInterface.View {
    @BindView(R.id.toolbar)
    Toolbar toolbarApps;
    @BindView(R.id.toolbarIcon)
    ImageView toolbarIconPage;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitlePage;
    //progress
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.titleProgress)
    TextView titleProgressBar;
    //field2
    @BindView(R.id.edTextNamaMeja)
    EditText namaMeja;
    @BindView(R.id.edTextJumlahKursi)
    EditText jumlahKursi;
    @Inject
    AddTableRestoPresenter addTableRestoPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_tambah_meja);
        ButterKnife.bind(this);
        DaggerAddTableRestoActivityComponent.builder()
                .netComponent(((App)getApplicationContext()).getNetComponent())
                .addTableRestoActivityModule(new AddTableRestoActivityModule(this,this))
                .build().inject(this);
        setSupportActionBar(toolbarApps);
        toolbarIconPage.setImageResource(R.drawable.icon_table_resto);
        toolbarTitlePage.setText("Tambah Data Meja");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void resultAddMeja(boolean status) {
        if (status){
            Toast.makeText(getApplicationContext(),"data berhasil disimpan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"data gagal disimpan",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    @OnClick(R.id.btnSimpanMasterMeja)
    public void simpan() {
        if(namaMeja.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field nama meja tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        if(jumlahKursi.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"field jumlah kursi tidak boleh kosong.",Toast.LENGTH_LONG).show();
        }
        else
        {
            Meja meja=new Meja();
            meja.setIdMeja("");
            meja.setNamaMeja(namaMeja.getText().toString());
            meja.setJumlahKursi(jumlahKursi.getText().toString());
            addTableRestoPresenter.tambahMeja(meja,"NEW");
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        titleProgressBar.setVisibility(View.VISIBLE);
        titleProgressBar.setText("sedang menyimpan data..");

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        titleProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMejaList() {
        Intent intent=new Intent(AddTableRestoActivity.this,ListTableRestoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
