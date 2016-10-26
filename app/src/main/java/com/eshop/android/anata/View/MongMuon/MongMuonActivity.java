package com.eshop.android.anata.View.MongMuon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.eshop.android.anata.Adapter.AdapterMongMuon;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.MongMuon.PresenterLogicMongMuon;
import com.eshop.android.anata.R;

import java.util.List;

public class MongMuonActivity extends AppCompatActivity implements ViewMongMuon {

    RecyclerView recyclerMongMuon;
    Toolbar toolbar;
    PresenterLogicMongMuon presenterLogicMongMuon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mongmuon);
        recyclerMongMuon = (RecyclerView) findViewById(R.id.recyclerMongMuon);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenterLogicMongMuon = new PresenterLogicMongMuon(this);
        presenterLogicMongMuon.LayDanhSachSanPhamMongMuon(this);
    }


    @Override
    public void HienThiDanhSachSanPhamTrongMongMuon(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMongMuon.setLayoutManager(layoutManager);
        AdapterMongMuon adapterMongMuon = new AdapterMongMuon(this, sanPhamList);
        recyclerMongMuon.setAdapter(adapterMongMuon);
        adapterMongMuon.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu,menu);
        return true;
    }
}
