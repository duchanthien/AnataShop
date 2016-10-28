package com.eshop.android.anata.View.ThanhToan;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eshop.android.anata.Model.ObjectClass.ChiTietHoaDon;
import com.eshop.android.anata.Model.ObjectClass.HoaDon;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan {

    EditText edtTenNguoiNhan, edtDiaChiNguoiNhan, edtSoDienThoaiNguoiNhan;
    ImageView imgTienMat, imgChuyenKhoan;
    Button btnThanhToan;
    Toolbar toolbar;
    CheckBox chkThoaThuan;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
    TextView txtTienMat, txtChuyenKhoan;

    int chonHinhThuc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);
        edtTenNguoiNhan = (EditText) findViewById(R.id.edtTenNguoiNhan);
        edtDiaChiNguoiNhan = (EditText) findViewById(R.id.edtDiaChi);
        edtSoDienThoaiNguoiNhan = (EditText) findViewById(R.id.edtSoDienThoai);
        imgTienMat = (ImageView) findViewById(R.id.imgTienMat);
        imgChuyenKhoan = (ImageView) findViewById(R.id.imgChuyenKhoan);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        chkThoaThuan = (CheckBox) findViewById(R.id.chkCamKet);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTienMat = (TextView) findViewById(R.id.txtTienMat);
        txtChuyenKhoan = (TextView) findViewById(R.id.txtChuyenKhoan);
        setSupportActionBar(toolbar);
        imgChuyenKhoan.setOnClickListener(this);
        imgTienMat.setOnClickListener(this);
        btnThanhToan.setOnClickListener(this);
        presenterLogicThanhToan = new PresenterLogicThanhToan(this, this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();
        ChonHinhThucGiaoHang(txtTienMat, txtChuyenKhoan);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imgTienMat:
                ChonHinhThucGiaoHang(txtTienMat, txtChuyenKhoan);
                chonHinhThuc = 0;
                break;
            case R.id.imgChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan, txtTienMat);
                chonHinhThuc = 1;
                break;
            case R.id.btnThanhToan:
                String tennguoinhan = edtTenNguoiNhan.getText().toString();
                String sodienthoai = edtSoDienThoaiNguoiNhan.getText().toString();
                String diachinguoinhan = edtDiaChiNguoiNhan.getText().toString();
                if (tennguoinhan.trim().length() > 0 && diachinguoinhan.trim().length() > 0 && sodienthoai.trim().length() > 0) {
                    if (chkThoaThuan.isChecked()) {
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTENNGUOINHAN(tennguoinhan);
                        hoaDon.setDIACHI(diachinguoinhan);
                        hoaDon.setSODT(sodienthoai);
                        hoaDon.setHINHTHUCTHANHTOAN(chonHinhThuc);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDons);
                        presenterLogicThanhToan.ThemHoaDon(hoaDon);
                    } else {
                        Toast.makeText(ThanhToanActivity.this, "Bạn chưa chọn vào cam kết !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThanhToanActivity.this, "Bạn chưa nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon) {
        txtDuocChon.setTextColor(getIdColor(R.color.facebookcolor));
        txtHuyChon.setTextColor(getIdColor(R.color.black));
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

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(ThanhToanActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(ThanhToanActivity.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        for (int i = 0; i < sanPhamList.size(); i++) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMASP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSOLUONG(sanPhamList.get(i).getSOLUONG());
            chiTietHoaDons.add(chiTietHoaDon);
        }
    }
}
