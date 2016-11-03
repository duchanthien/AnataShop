package com.eshop.android.anata.View.TrangDanhMucSanPham;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eshop.android.anata.Adapter.AdapterTopDienThoaiDienTu;
import com.eshop.android.anata.Model.ObjectClass.ILoadMore;
import com.eshop.android.anata.Model.ObjectClass.LoadMoreScroll;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.TrangDanhMucSanPham.PresenterLogicSanPhamTheoDanhMuc;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.GioHang.GioHangActivity;
import com.eshop.android.anata.View.TimKiem.TimKiemActivity;

import java.util.List;

public class SanPhamTheoDanhMucFragment extends Fragment implements ViewHienThiSanPhamTheoDanhMuc, OnClickListener, ILoadMore {

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
    TextView txtGioHang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanphamtheodanhmuc, container, false);
        setHasOptionsMenu(false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_sanphamtheodanhmuc);
        btnThayDoiRecyclerView = (Button) view.findViewById(R.id.btnThayDoiRecyclerView);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarDanhMuc);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAITH", 0);
        String tensp = bundle.getString("TENLOAITH");
        kiemtra = bundle.getBoolean("KIEMTRA", false);

        sanPhamTheoDanhMuc = new PresenterLogicSanPhamTheoDanhMuc(SanPhamTheoDanhMucFragment.this);
        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);

        btnThayDoiRecyclerView.setOnClickListener(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle(tensp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_trangchu, menu);
        MenuItem iGioHang = menu.findItem(R.id.itGiohang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoluongSanPhamGioHang);

        //txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        int dem = sanPhamTheoDanhMuc.DemSanPhamCoTrongGioHang(getContext());
        if (dem > 0) {
            txtGioHang.setText(String.valueOf(dem));
            txtGioHang.setVisibility(View.VISIBLE);
        } else {
            txtGioHang.setVisibility(View.INVISIBLE);
        }
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TrangChuActivity.this,"Check Click",Toast.LENGTH_SHORT).show();
                Intent iGioHang = new Intent(getContext(), GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        MenuItem iTimKiem = menu.findItem(R.id.itSearch);
        iTimKiem.setVisible(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itSearch:
                Intent iTimKiem = new Intent(getContext(), TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
        }
        return true;
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        this.sanPhams = sanPhamList;
        if (dangGrid) {
            layoutManager = new GridLayoutManager(getContext(), 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), R.layout.custom_layout_topdienthoaimaytinhbang, sanPhams);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhams);
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


}
