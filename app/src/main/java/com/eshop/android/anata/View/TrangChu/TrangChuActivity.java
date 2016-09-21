package com.eshop.android.anata.View.TrangChu;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.eshop.android.anata.Adapter.ExpandAdapter;
import com.eshop.android.anata.Adapter.ViewPagerAdapter;
import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
import com.eshop.android.anata.Model.ObjectClass.LoaiSanPham;
import com.eshop.android.anata.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.DangNhapDangKy.DangNhapActivity;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentDienTu;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentLamDep;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentMeVaBe;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentNoiBat;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentThoiTrang;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentThuongHieu;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
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

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener, OnOffsetChangedListener {

    // 192.168.1.102
    public static String SERVER_NAME = "http://192.168.80.1:8080/webanata/loaisanpham.php";

    Toolbar toolbar;
    TabLayout mtabLayout;
    ViewPager mviewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXuLyMenu presenterLogicXuLyMenu;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    MenuItem ItemDangnhap, ItemDangxuat;
    ModelDangNhap modelDangNhap;
    GoogleApiClient googleApiClient;
    GoogleSignInResult googleSignInResult;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.trangchu_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mviewPager = (ViewPager) findViewById(R.id.viewpager);
        mtabLayout = (TabLayout) findViewById(R.id.tabs);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        expandableListView = (ExpandableListView) findViewById(R.id.epMenu);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnSearch);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        setupViewPager(mviewPager);

        mtabLayout.setupWithViewPager(mviewPager);

        drawerToggle = new ActionBarDrawerToggle(TrangChuActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();


        presenterLogicXuLyMenu = new PresenterLogicXuLyMenu(TrangChuActivity.this);
        modelDangNhap = new ModelDangNhap();
        presenterLogicXuLyMenu.LayDanhSachMenu();
        googleApiClient = modelDangNhap.layGoogleApiClient(TrangChuActivity.this, this);

        appBarLayout.addOnOffsetChangedListener(this);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentNoiBat(), "Nổi bật");
        adapter.addFragment(new FragmentChuongTrinhKhuyenMai(), "Chương trình khuyến mãi");
        adapter.addFragment(new FragmentDienTu(), "Điện tử");
        adapter.addFragment(new FragmentNhaCuaVaDoiSong(), "Nhà cửa & Đời sống");
        adapter.addFragment(new FragmentMeVaBe(), "Mẹ & Bé");
        adapter.addFragment(new FragmentLamDep(), "Làm đẹp");
        adapter.addFragment(new FragmentThoiTrang(), "Thời trang");
        adapter.addFragment(new FragmentTheThaoVaDuLich(), "Thể thao & Du lịch");
        adapter.addFragment(new FragmentThuongHieu(), "Thương hiệu");
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
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
        String tennv = modelDangNhap.LayCachedDangNhap(TrangChuActivity.this);

        if (!tennv.equals("")) {
            ItemDangnhap.setTitle(tennv);
        }
        if (googleSignInResult != null) {
            ItemDangnhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }

        if (accessToken != null || googleSignInResult != null || !tennv.equals("")) {
            ItemDangxuat.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(TrangChuActivity.this).equals("")) {
                    Intent iDangNhap = new Intent(TrangChuActivity.this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.itDangXuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(googleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (!modelDangNhap.LayCachedDangNhap(TrangChuActivity.this).equals("")){
                    modelDangNhap.CapNhatCachedDangNhap(TrangChuActivity.this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }


        }

        return true;
    }


    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> list) {
        ExpandAdapter expandAdapter = new ExpandAdapter(TrangChuActivity.this, list);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (collapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            linearLayout.animate().alpha(0).setDuration(200);
        } else {
            linearLayout.animate().alpha(1).setDuration(200);
        }
    }
}
