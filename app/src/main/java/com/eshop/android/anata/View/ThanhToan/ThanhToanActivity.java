package com.eshop.android.anata.View.ThanhToan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eshop.android.anata.Model.ObjectClass.ChiTietHoaDon;
import com.eshop.android.anata.Model.ObjectClass.HoaDon;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.eshop.android.anata.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan {

    EditText edtTenNguoiNhan, edtDiaChiNguoiNhan, edtSoDienThoaiNguoiNhan;
    ImageView imgShip, imgVisa;
    Button btnThanhToan;
    Toolbar toolbar;
    CheckBox chkThoaThuan;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);
        edtTenNguoiNhan = (EditText) findViewById(R.id.edtTenNguoiNhan);
        edtDiaChiNguoiNhan = (EditText) findViewById(R.id.edtDiaChi);
        edtSoDienThoaiNguoiNhan = (EditText) findViewById(R.id.edtSoDienThoai);
        imgShip = (ImageView) findViewById(R.id.imgShip);
        imgVisa = (ImageView) findViewById(R.id.imgVisa);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        chkThoaThuan = (CheckBox) findViewById(R.id.chkCamKet);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgVisa.setOnClickListener(this);
        imgShip.setOnClickListener(this);
        btnThanhToan.setOnClickListener(this);
        presenterLogicThanhToan = new PresenterLogicThanhToan(this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imgShip:

                break;
            case R.id.imgVisa:

                break;
            case R.id.btnThanhToan:
                String tennguoinhan = edtTenNguoiNhan.getText().toString();
                String diachinguoinhan = edtDiaChiNguoiNhan.getText().toString();
                String sodienthoai = edtSoDienThoaiNguoiNhan.getText().toString();
                if (tennguoinhan.trim().length() > 0 && diachinguoinhan.trim().length() > 0 && sodienthoai.trim().length() > 0) {
                    if (chkThoaThuan.isChecked()) {
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTENNGUOINHAN(tennguoinhan);
                        hoaDon.setDIACHI(diachinguoinhan);
                        hoaDon.setSODT(sodienthoai);
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

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(ThanhToanActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(ThanhToanActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();

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
