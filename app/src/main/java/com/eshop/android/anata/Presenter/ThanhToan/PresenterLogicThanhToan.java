package com.eshop.android.anata.Presenter.ThanhToan;

import android.content.Context;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.ObjectClass.HoaDon;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ThanhToan.ModelThanhToan;
import com.eshop.android.anata.View.ThanhToan.ViewThanhToan;

import java.util.List;

/**
 * Created by Han on 26/10/2016.
 */

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
    }


    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if(kiemtra){
            viewThanhToan.DatHangThanhCong();
        }else{
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size() > 0){
            viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);

        }
    }
}
