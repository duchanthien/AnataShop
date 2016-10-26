package com.eshop.android.anata.View.DanhGia;

import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Presenter.DanhGia.PresenterLogicDanhGia;
import com.eshop.android.anata.R;

import java.util.List;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {

    TextInputLayout input_edtTieuDe, input_edtNoiDung;
    EditText edtTieuDe, edtNoiDung;
    RatingBar rbDanhGia;
    int masp = 0;
    int sosao = 0;
    Button btnDongYDanhGia;

     PresenterLogicDanhGia presenterLogicThemDanhGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgia);
        input_edtTieuDe = (TextInputLayout) findViewById(R.id.ipEdtTieuDe);
        input_edtNoiDung = (TextInputLayout) findViewById(R.id.ipedtNoiDungDanhGia);
        edtTieuDe = (EditText) findViewById(R.id.edtTieude);
        edtNoiDung = (EditText) findViewById(R.id.edtNoiDung);
        rbDanhGia = (RatingBar) findViewById(R.id.rbDanhGiaSanPham);
        btnDongYDanhGia = (Button) findViewById(R.id.btnDongYDanhGia);

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicThemDanhGia = new PresenterLogicDanhGia(this);
        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Thêm đánh giá thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể thêm đánh giá này", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGias) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String madg = telephonyManager.getDeviceId();
        String tenthietbi = Build.MODEL;
        String tieude = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        if(tieude.trim().length() > 0 ){
            input_edtTieuDe.setErrorEnabled(false);
            input_edtTieuDe.setError("");
        }else{
            input_edtTieuDe.setErrorEnabled(true);
            input_edtTieuDe.setError("Bạn chưa nhập tiêu đề");
        }
        if(noidung.trim().length() > 0){
            input_edtNoiDung.setErrorEnabled(false);
            input_edtNoiDung.setError("");
        }else{
            input_edtNoiDung.setErrorEnabled(true);
            input_edtNoiDung.setError("Bạn chưa nhập nội dung");
        }
        if(!input_edtNoiDung.isErrorEnabled() && !input_edtTieuDe.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMADG(madg);
            danhGia.setMASP(masp);
            danhGia.setTENTHIETBI(tenthietbi);
            danhGia.setTIEUDE(tieude);
            danhGia.setNOIDUNG(noidung);
            danhGia.setSOSAO(sosao);
            presenterLogicThemDanhGia.ThemDanhGia(danhGia);

        }

    }
}
