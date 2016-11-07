    package com.eshop.android.anata.View.MongMuon;

    import android.content.DialogInterface;
    import android.content.Intent;
    import android.support.v4.view.MenuItemCompat;
    import android.support.v7.app.AlertDialog;
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

    import com.eshop.android.anata.Adapter.AdapterMongMuon;
    import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
    import com.eshop.android.anata.Model.ObjectClass.SanPham;
    import com.eshop.android.anata.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
    import com.eshop.android.anata.Presenter.MongMuon.PresenterLogicMongMuon;
    import com.eshop.android.anata.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
    import com.eshop.android.anata.R;
    import com.eshop.android.anata.View.DangNhapDangKy.DangNhapActivity;
    import com.eshop.android.anata.View.GioHang.GioHangActivity;
    import com.eshop.android.anata.View.TimKiem.TimKiemActivity;
    import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
    import com.facebook.AccessToken;
    import com.facebook.GraphRequest;
    import com.facebook.GraphResponse;
    import com.facebook.login.LoginManager;
    import com.google.android.gms.auth.api.Auth;
    import com.google.android.gms.auth.api.signin.GoogleSignInResult;
    import com.google.android.gms.common.ConnectionResult;
    import com.google.android.gms.common.api.GoogleApiClient;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.List;

    public class MongMuonActivity extends AppCompatActivity implements ViewMongMuon,GoogleApiClient.OnConnectionFailedListener {

        RecyclerView recyclerMongMuon;
        Toolbar toolbar;
        PresenterLogicMongMuon presenterLogicMongMuon;
        Button btnTiepTucMuaHang;
        TextView txtGioHang;
        String tennguoidung = "";
        AccessToken accessToken;
        Menu menu;
        MenuItem ItemDangnhap, ItemDangxuat;
        ModelDangNhap modelDangNhap;
        GoogleApiClient googleApiClient;
        GoogleSignInResult googleSignInResult;
        PresenterLogicXuLyMenu presenterLogicXuLyMenu;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_mongmuon);
            recyclerMongMuon = (RecyclerView) findViewById(R.id.recyclerMongMuon);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            presenterLogicMongMuon = new PresenterLogicMongMuon(this);
            presenterLogicMongMuon.LayDanhSachSanPhamMongMuon(this);
            presenterLogicXuLyMenu = new PresenterLogicXuLyMenu();
            modelDangNhap = new ModelDangNhap();
            googleApiClient = modelDangNhap.layGoogleApiClient(MongMuonActivity.this, this);
            btnTiepTucMuaHang = (Button) findViewById(R.id.btnTiepTucMuaHang);
            btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
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
            getMenuInflater().inflate(R.menu.menu_trangchu, menu);
            this.menu = menu;

            ItemDangnhap = menu.findItem(R.id.itDangNhap);
            ItemDangxuat = menu.findItem(R.id.itDangXuat);

            accessToken = presenterLogicXuLyMenu.LayTokenNguoiDungFacebook();
            googleSignInResult = modelDangNhap.LayThongTinDangNhapGoogle(googleApiClient);

            if (accessToken != null) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            tennguoidung = object.getString("name");
                            ItemDangnhap.setTitle(tennguoidung);
                            Log.d("kiemtra", tennguoidung);
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
            String tennv = modelDangNhap.LayCachedDangNhap(MongMuonActivity.this);

            if (!tennv.equals("")) {
                ItemDangnhap.setTitle(tennv);
            }
            if (googleSignInResult != null) {
                ItemDangnhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
            }

            if (accessToken != null || googleSignInResult != null || !tennv.equals("")) {
                ItemDangxuat.setVisible(true);
            }


            MenuItem itTimKiem = menu.findItem(R.id.itSearch);
            itTimKiem.setVisible(true);
            MenuItem iGioHang = menu.findItem(R.id.itGiohang);
            View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
            txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoluongSanPhamGioHang);

            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            int dem = presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this);
            txtGioHang.setText("");
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
                    Intent iGioHang = new Intent(MongMuonActivity.this, GioHangActivity.class);
                    startActivity(iGioHang);
                }
            });

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.itDangNhap:
                    if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(MongMuonActivity.this).equals("")) {
                        Intent iDangNhap = new Intent(MongMuonActivity.this, DangNhapActivity.class);
                        startActivity(iDangNhap);
                    }
                    break;
                case R.id.itDangXuat:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Bạn có chắc là muốn đăng xuất");
                    builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (accessToken != null) {
                                LoginManager.getInstance().logOut();
                                MongMuonActivity.this.menu.clear();
                                MongMuonActivity.this.onCreateOptionsMenu(MongMuonActivity.this.menu);
                            }

                            if (googleSignInResult != null) {
                                Auth.GoogleSignInApi.signOut(googleApiClient);
                                MongMuonActivity.this.menu.clear();
                                MongMuonActivity.this.onCreateOptionsMenu(MongMuonActivity.this.menu);
                            }
                            if (!modelDangNhap.LayCachedDangNhap(MongMuonActivity.this).equals("")) {
                                modelDangNhap.CapNhatCachedDangNhap(MongMuonActivity.this, "");
                                MongMuonActivity.this.menu.clear();
                                MongMuonActivity.this.onCreateOptionsMenu(MongMuonActivity.this.menu);
                            }
                        }
                    });
                    builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                    break;
                case R.id.itSearch:
                    Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                    startActivity(iTimKiem);
                    break;
            }

            return true;
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {

        }
    }
