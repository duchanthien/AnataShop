package com.eshop.android.anata.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eshop.android.anata.Adapter.AdapterDanhGia;
import com.eshop.android.anata.Adapter.AdapterViewPagerSlider;
import com.eshop.android.anata.Model.GioHang_MongMuon.ModelMongMuon;
import com.eshop.android.anata.Model.ObjectClass.ChiTietSanPham;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.eshop.android.anata.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.DanhGia.DanhSachDanhGiaActivity;
import com.eshop.android.anata.View.DanhGia.ThemDanhGiaActivity;
import com.eshop.android.anata.View.GioHang.GioHangActivity;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragments;
    TextView txtTensanpham, txtGiatien, txtTenCuaHangDongGoi, txtThongTinChiTiet, txtVietDanhGia, txtXemTatCaNhanXet, txtGioHang;
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
    boolean kiemtraMongMuon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        viewPager = (ViewPager) findViewById(R.id.viewPagerSlider);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        txtTensanpham = (TextView) findViewById(R.id.txtTenSanPham);
        txtGiatien = (TextView) findViewById(R.id.txtGiaTien);
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        masp = getIntent().getIntExtra("masp", 0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp, 0);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);
        imgThemMongMuon.setOnClickListener(this);
        imgChiaSe.setOnClickListener(this);


    }


    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();
        sanPhamGioHang = sanPham;
        // String[] linkhinh = sanPham.getHINHNHO().split(",");
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        txtTensanpham.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        txtGiatien.setText(gia + " VNĐ");
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
            Log.d("Kiemtra", chiTietSanPhamList.get(i).getTenchitiet());

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

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TrangChuActivity.this,"Check Click",Toast.LENGTH_SHORT).show();
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

}
