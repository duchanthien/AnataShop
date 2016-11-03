package com.eshop.android.anata.View.DanhGia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.eshop.android.anata.Adapter.AdapterDanhGia;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Model.ObjectClass.ILoadMore;
import com.eshop.android.anata.Model.ObjectClass.LoadMoreScroll;
import com.eshop.android.anata.Presenter.DanhGia.PresenterLogicDanhGia;
import com.eshop.android.anata.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {

    RecyclerView recyclerDanhSachDanhGia;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> danhGiaList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachdanhgia);
        recyclerDanhSachDanhGia = (RecyclerView) findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        masp = getIntent().getIntExtra("masp", 0);
        danhGiaList = new ArrayList<>();
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaSanPham(masp, 0, progressBar);

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGias) {
        danhGiaList.addAll(danhGias);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGiaList, 0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterDanhGia.notifyDataSetChanged();

    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaSanPham(masp, tongitem, progressBar);
    }
}
