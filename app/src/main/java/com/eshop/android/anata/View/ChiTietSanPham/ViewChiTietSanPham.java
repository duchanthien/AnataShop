package com.eshop.android.anata.View.ChiTietSanPham;

import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Han on 18/10/2016.
 */

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);

    void HienSliderSanPham(String[] linkHinhsanpham);

    void HienThiDanhGia(List<DanhGia> danhGias);

    void ThemGioHangThanhCong();

    void ThemGioHangThatBai();

    void ThemMongMuonThanhCong();

    void ThemMongMuonThatBai();

    void XoaMongMuonThanhCong();

    void XoaMongMuonThatBai();

}
