package com.eshop.android.anata.View.TrangDanhMucSanPham;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.eshop.android.anata.Adapter.AdapterTopDienThoaiDienTu;
import com.eshop.android.anata.Model.ObjectClass.ILoadMore;
import com.eshop.android.anata.Model.ObjectClass.LoadMoreScroll;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.TrangDanhMucSanPham.PresenterLogicSanPhamTheoDanhMuc;
import com.eshop.android.anata.R;

import java.util.List;

public class SanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, OnClickListener, ILoadMore {

    RecyclerView recyclerView;
    Button btnThayDoiRecyclerView;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    int masp;
    boolean kiemtra;
    PresenterLogicSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;

    Toolbar toolbar;
    List<SanPham> sanPhams;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamtheodanhmuc);

        recyclerView = (RecyclerView) findViewById(R.id.rcv_sanphamtheodanhmuc);
        btnThayDoiRecyclerView = (Button) findViewById(R.id.btnThayDoiRecyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbarDanhMuc);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Intent intent = getIntent();
        masp = intent.getIntExtra("MALOAITH", 0);
        String tensp = intent.getStringExtra("TENLOAITH");
        kiemtra = intent.getBooleanExtra("KIEMTRA", false);

        sanPhamTheoDanhMuc = new PresenterLogicSanPhamTheoDanhMuc(SanPhamTheoDanhMucActivity.this);
        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);

        btnThayDoiRecyclerView.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        this.sanPhams = sanPhamList;
        if (dangGrid) {
            layoutManager = new GridLayoutManager(SanPhamTheoDanhMucActivity.this, 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(SanPhamTheoDanhMucActivity.this, R.layout.custom_layout_topdienthoaimaytinhbang, sanPhams);
        } else {
            layoutManager = new LinearLayoutManager(SanPhamTheoDanhMucActivity.this);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(SanPhamTheoDanhMucActivity.this, R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhams);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnThayDoiRecyclerView:
                dangGrid = !dangGrid;
                sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);
                ;
                break;
        }
    }


    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> sanPhamsLoadMore = sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(masp, kiemtra, tongitem, progressBar);
        sanPhams.addAll(sanPhamsLoadMore);
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

}
