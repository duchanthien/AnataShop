package com.eshop.android.anata.View.ChiTietSanPham;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eshop.android.anata.Adapter.AdapterDanhGia;
import com.eshop.android.anata.Adapter.AdapterViewPagerSlider;
import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
import com.eshop.android.anata.Model.GioHang_MongMuon.ModelMongMuon;
import com.eshop.android.anata.Model.ObjectClass.ChiTietKhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.ChiTietSanPham;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.eshop.android.anata.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.DangNhapDangKy.DangNhapActivity;
import com.eshop.android.anata.View.DanhGia.DanhSachDanhGiaActivity;
import com.eshop.android.anata.View.DanhGia.ThemDanhGiaActivity;
import com.eshop.android.anata.View.GioHang.GioHangActivity;
import com.eshop.android.anata.View.MongMuon.MongMuonActivity;
import com.eshop.android.anata.View.ThanhToan.ThanhToanActivity;
import com.eshop.android.anata.View.TimKiem.TimKiemActivity;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragments;
    TextView txtTensanpham, txtGiatien, txtGiamGia, txtTenCuaHangDongGoi, txtThongTinChiTiet, txtVietDanhGia, txtXemTatCaNhanXet, txtGioHang;
    Toolbar toolbar;
    ImageView imgXemThemChiTiet, imgThemMongMuon, imgChiaSe;

    boolean kiemtraXochitiet = false;
    LinearLayout lnThongSoKyThuat;
    RecyclerView recyclerDanhGiaChiTiet;
    int masp;
    RatingBar ratingBar;
    ImageButton imgThemGioHang;
    SanPham sanPhamGioHang;
    public static int soSanPhamTrongGioHang;
    boolean onPause = false;
    public static boolean kiemtraMongMuon = false;

    Button btnMuaNgay, btnBaoVeNguoiMuahang;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    MenuItem ItemDangnhap, ItemDangxuat;
    ModelDangNhap modelDangNhap;
    GoogleApiClient googleApiClient;
    GoogleSignInResult googleSignInResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        viewPager = (ViewPager) findViewById(R.id.viewPagerSlider);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        txtTensanpham = (TextView) findViewById(R.id.txtTenSanPham);
        txtGiatien = (TextView) findViewById(R.id.txtGiaTien);
        txtGiamGia = (TextView) findViewById(R.id.txtGiamGia);
        txtTenCuaHangDongGoi = (TextView) findViewById(R.id.txtCuaHangDongGoi);
        txtThongTinChiTiet = (TextView) findViewById(R.id.txtThongTinChiTiet);
        txtVietDanhGia = (TextView) findViewById(R.id.txtVietDanhGia);
        txtXemTatCaNhanXet = (TextView) findViewById(R.id.txtXemTatCaNhanXet);
        imgXemThemChiTiet = (ImageView) findViewById(R.id.imXemThemChiTiet);
        lnThongSoKyThuat = (LinearLayout) findViewById(R.id.lnThongSoKyThuat);
        recyclerDanhGiaChiTiet = (RecyclerView) findViewById(R.id.recyclerDanhSachDanhGia);
        ratingBar = (RatingBar) findViewById(R.id.rbDangGia);
        imgThemGioHang = (ImageButton) findViewById(R.id.imgThemGioHang);
        imgThemMongMuon = (ImageView) findViewById(R.id.imgMongMuon);
        imgChiaSe = (ImageView) findViewById(R.id.imgChiaSe);
        toolbar = (Toolbar) findViewById(R.id.toolbar_chitiet);
        btnMuaNgay = (Button) findViewById(R.id.btnMuaNgay);
        btnBaoVeNguoiMuahang = (Button) findViewById(R.id.btnBaoVeNguoiMuaHang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        masp = getIntent().getIntExtra("masp", 0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp, 0);

        modelDangNhap = new ModelDangNhap();
        googleApiClient = modelDangNhap.layGoogleApiClient(this, this);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);
        imgThemMongMuon.setOnClickListener(this);
        imgChiaSe.setOnClickListener(this);
        btnMuaNgay.setOnClickListener(this);
        btnBaoVeNguoiMuahang.setOnClickListener(this);


    }


    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();
        sanPhamGioHang = sanPham;
        // String[] linkhinh = sanPham.getHINHNHO().split(",");
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        txtTensanpham.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();

        int giatien = sanPham.getGIA();

        if (chiTietKhuyenMai != null) {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if (phantramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);
                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamGia.setText(gia + " VNĐ ");
                giatien = giatien * phantramkm / 100;
            }
        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGiatien.setText(gia + " VNĐ ");
        txtTenCuaHangDongGoi.setText(sanPham.getTENNV());
        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0, 100));

        if (sanPham.getTHONGTIN().length() < 100) {
            imgXemThemChiTiet.setVisibility(View.GONE);

        } else {
            imgXemThemChiTiet.setVisibility(View.VISIBLE);
            imgXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraXochitiet = !kiemtraXochitiet;
                    if (kiemtraXochitiet) {
                        // sau khi mở chi tiết
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);
                    } else {
                        // đóng chi tiết
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0, 100));
                        imgXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }

    }

    private void HienThiThongSoKyThuat(SanPham sanPham) {
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();

        TextView txtTieuDeThongSoKyThuat = new TextView(this);
        txtTieuDeThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtTieuDeThongSoKyThuat.setText("");
        lnThongSoKyThuat.addView(txtTieuDeThongSoKyThuat);

        for (int i = 0; i < chiTietSanPhamList.size(); i++) {
            //Log.d("Kiemtra", chiTietSanPhamList.get(i).getTenchitiet());

            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtTenThongSo.setText(chiTietSanPhamList.get(i).getTenchitiet());

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhamList.get(i).getGiatri());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }

    @Override
    public void HienSliderSanPham(String[] linkHinhsanpham) {
        fragments = new ArrayList<>();

        for (int i = 0; i < linkHinhsanpham.length; i++) {
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangChuActivity.SERVER + linkHinhsanpham[i]);

            fragmentSliderChiTietSanPham.setArguments(bundle);
            fragments.add(fragmentSliderChiTietSanPham);

        }
        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        addDotSlider(0);
        viewPager.addOnPageChangeListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);

        MenuItem iGioHang = menu.findItem(R.id.itGiohang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoluongSanPhamGioHang);

        //txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        int dem = presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this);
        if (dem > 0) {
            txtGioHang.setText(String.valueOf(dem));
            txtGioHang.setVisibility(View.VISIBLE);
        } else {
            txtGioHang.setVisibility(View.INVISIBLE);
            txtGioHang.setText(String.valueOf(dem));
        }
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TrangChuActivity.this,"Check Click",Toast.LENGTH_SHORT).show();
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        MenuItem iTimKiem = menu.findItem(R.id.itSearch);
        iTimKiem.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(this).equals("")) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
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
                if (!modelDangNhap.LayCachedDangNhap(this).equals("")) {
                    modelDangNhap.CapNhatCachedDangNhap(this, "");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;
            case R.id.itMongMuon:
                Intent iMongMuon = new Intent(this, MongMuonActivity.class);
                startActivity(iMongMuon);
                break;
            case R.id.itSearch:
                Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;

        }
        return true;
    }

    private void addDotSlider(int vitrihientai) {
        txtDots = new TextView[fragments.size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < fragments.size(); i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActivie));

            layoutDots.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }

    private int getIdColor(int idColor) {

        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idColor);
        } else {
            color = getResources().getColor(idColor);
        }

        return color;
    }

    private Drawable getHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this, idDrawable);
        } else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Fragment fragment;
        View view;
        ImageView imageView;
        Bitmap bitmap;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] hinhsanphamgiohang;
        switch (id) {
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("masp", masp);
                startActivity(iThemDanhGia);
                break;
            case R.id.txtXemTatCaNhanXet:
                Intent iDanhSachDanhGia = new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp", masp);

                startActivity(iDanhSachDanhGia);
                break;
            case R.id.imgThemGioHang:
                fragment = fragments.get(0);
                view = fragment.getView();
                imageView = (ImageView) view.findViewById(R.id.imgSlider);
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);
                sanPhamGioHang.setSOLUONG(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this);
                txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
                txtGioHang.setVisibility(View.VISIBLE);
                break;
            case R.id.imgMongMuon:
                kiemtraMongMuon = !kiemtraMongMuon;
                if (kiemtraMongMuon) {
                    fragment = fragments.get(0);
                    view = fragment.getView();
                    imageView = (ImageView) view.findViewById(R.id.imgSlider);
                    bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] hinhsanphamgiohang1 = byteArrayOutputStream.toByteArray();
                    sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang1);
                    presenterLogicChiTietSanPham.ThemMongMuon(sanPhamGioHang, this);
                    imgThemMongMuon.setImageDrawable(getHinhChiTiet(R.drawable.ic_favorite_black_24dp));
                } else {
                    presenterLogicChiTietSanPham.XoaMongMuon(sanPhamGioHang.getMASP(), this);
                    imgThemMongMuon.setImageDrawable(getHinhChiTiet(R.drawable.ic_favorite_border_black_24dp));
                }

                break;
            case R.id.imgChiaSe:
                /*fragment = fragments.get(0);
                view = fragment.getView();
                imageView = (ImageView) view.findViewById(R.id.imgSlider);
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                SharePhoto sharePhoto1 = new SharePhoto.Builder()
                        .setBitmap(bitmap).build();*/

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(txtTensanpham.getText().toString())
                        .setContentDescription(
                                txtGiatien.getText().toString())
                        .setContentUrl(Uri.parse("https://www.facebook.com/duc.hanthien"))
                        .build();
                /*ShareContent shareContent = new ShareMediaContent.Builder()
                        .addMedium(sharePhoto1)
                        .build();*/
                ShareDialog shareDialog = new ShareDialog(this);
                //shareDialog.show(shareContent, ShareDialog.Mode.AUTOMATIC);
                shareDialog.show(linkContent);


                break;
            case R.id.btnMuaNgay:
                fragment = fragments.get(0);
                view = fragment.getView();
                imageView = (ImageView) view.findViewById(R.id.imgSlider);
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);
                sanPhamGioHang.setSOLUONG(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this);

                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(this).equals("")) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                } else {
                    Intent iThanhToan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                    startActivity(iThanhToan);
                }
                break;
            case R.id.btnBaoVeNguoiMuaHang:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bảo vệ người mua hàng");
                View viewDialog = getLayoutInflater().inflate(R.layout.custom_dialog_chitietsanpham, null, false);
                builder.setView(viewDialog);
                TextView txtNoidung = (TextView) viewDialog.findViewById(R.id.txtNoiDungBaoVe);
                TextView txtNoidung1 = (TextView) viewDialog.findViewById(R.id.txtNoiDungBaoVe2);
                TextView txtNoidung2 = (TextView) viewDialog.findViewById(R.id.txtNoiDungBaoVe3);
                TextView txtNoidung3 = (TextView) viewDialog.findViewById(R.id.txtNoiDungBaoVe4);
                String[] mangNoidung = getResources().getStringArray(R.array.mang_noidungbaov);
                txtNoidung.setText(mangNoidung[0]);
                txtNoidung1.setText(mangNoidung[1]);
                txtNoidung2.setText(mangNoidung[2]);
                txtNoidung3.setText(mangNoidung[3]);
                builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGias) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGias, 3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhGiaChiTiet.setAdapter(adapterDanhGia);
        recyclerDanhGiaChiTiet.setLayoutManager(layoutManager);

        adapterDanhGia.notifyDataSetChanged();
        setRatingBar(danhGias);
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        soSanPhamTrongGioHang = presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this);
        txtGioHang.setText(String.valueOf(soSanPhamTrongGioHang));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ThemMongMuonThanhCong() {
        Toast.makeText(this, "Thêm vào sản phẩm yêu thích thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ThemMongMuonThatBai() {
        // Toast.makeText(this, "Thêm vào sản phẩm yêu thích thất công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void XoaMongMuonThanhCong() {
        Toast.makeText(this, "Xóa sản phẩm khỏi mục yêu thích thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void XoaMongMuonThatBai() {
        //Toast.makeText(this, "Xóa sản phẩm khỏi mục yêu thích thành công", Toast.LENGTH_SHORT).show();
    }

    public void setRatingBar(List<DanhGia> danhGias) {
        int sumSosao = 0;
        for (int i = 0; i < danhGias.size(); i++) {
            sumSosao += danhGias.get(i).getSOSAO();
        }
        ratingBar.setRating(sumSosao);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
