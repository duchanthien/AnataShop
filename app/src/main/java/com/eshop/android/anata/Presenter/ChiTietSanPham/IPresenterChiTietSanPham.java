package com.eshop.android.anata.Presenter.ChiTietSanPham;

import android.content.Context;

import com.eshop.android.anata.Model.ObjectClass.SanPham;

/**
 * Created by Han on 18/10/2016.
 */

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);

    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit);

    void ThemGioHang(SanPham sanPham, Context context);

    void ThemMongMuon(SanPham sanPham, Context context);

    void XoaMongMuon(int masp, Context context);

}
