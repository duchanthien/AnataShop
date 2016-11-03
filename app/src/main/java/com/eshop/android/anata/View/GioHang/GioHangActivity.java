package com.eshop.android.anata.View.GioHang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eshop.android.anata.Adapter.AdapterGioHang;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.GioHang.PresenterLogicGioHang;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {

    RecyclerView recyclerGioHang;
    Button btnMuaNgay;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerGioHang = (RecyclerView) findViewById(R.id.recylerGioHang);
        btnMuaNgay = (Button) findViewById(R.id.btnMuaNgay);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);
        btnMuaNgay.setOnClickListener(this);

    }


    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerGioHang.setLayoutManager(layoutManager);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhamList);
        recyclerGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnMuaNgay:
                int dem = presenterLogicGioHang.DemSanPhamTrongGioHang(this);
                if (dem > 0) {
                    Intent iThanhToan = new Intent(this, ThanhToanActivity.class);
                    startActivity(iThanhToan);
                }else{
                    Toast.makeText(this,"Bạn chưa mua hàng",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }
}
