package com.eshop.android.anata.View.GioHang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eshop.android.anata.Adapter.AdapterGioHang;
import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.GioHang.PresenterLogicGioHang;
import com.eshop.android.anata.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.DangNhapDangKy.DangNhapActivity;
import com.eshop.android.anata.View.ThanhToan.ThanhToanActivity;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang,GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    RecyclerView recyclerGioHang;
    Button btnMuaNgay;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    TextView txtTongTien;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    GoogleApiClient googleApiClient;
    GoogleSignInResult googleSignInResult;
    PresenterLogicXuLyMenu presenterLogicXuLyMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerGioHang = (RecyclerView) findViewById(R.id.recylerGioHang);
        btnMuaNgay = (Button) findViewById(R.id.btnMuaNgay);
        txtTongTien = (TextView) findViewById(R.id.txtTongTienGioHang);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);
        presenterLogicGioHang.LayTongTienTrongGioHang(this);
        presenterLogicXuLyMenu = new PresenterLogicXuLyMenu();
        modelDangNhap = new ModelDangNhap();
        googleApiClient = modelDangNhap.layGoogleApiClient(GioHangActivity.this, this);

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
    public void HienThiTongTienTrongGioHang(int tongtien) {
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String tong = numberFormat.format(tongtien).toString();
        txtTongTien.setText(tong+" VNĐ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        this.menu = menu;


        accessToken = presenterLogicXuLyMenu.LayTokenNguoiDungFacebook();
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGoogle(googleApiClient);

        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        //ItemDangnhap.setTitle(tennguoidung);
                        //Log.d("kiemtra", tennguoidung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");

            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();
        }
        String tennv = modelDangNhap.LayCachedDangNhap(GioHangActivity.this);

        if (!tennv.equals("")) {
           // ItemDangnhap.setTitle(tennv);
        }
        if (googleSignInResult != null) {
            //ItemDangnhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }

        if (accessToken != null || googleSignInResult != null || !tennv.equals("")) {
            //ItemDangxuat.setVisible(true);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnMuaNgay:
                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(GioHangActivity.this).equals("")) {
                    Intent iDangNhap = new Intent(GioHangActivity.this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }else {
                    int dem = presenterLogicGioHang.DemSanPhamTrongGioHang(this);
                    if (dem > 0) {
                        Intent iThanhToan = new Intent(this, ThanhToanActivity.class);
                        startActivity(iThanhToan);
                    } else {
                        Toast.makeText(this, "Bạn chưa mua hàng", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                break;
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
