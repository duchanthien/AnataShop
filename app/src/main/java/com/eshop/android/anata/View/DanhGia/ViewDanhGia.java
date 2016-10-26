package com.eshop.android.anata.View.DanhGia;

import com.eshop.android.anata.Model.ObjectClass.DanhGia;

import java.util.List;

/**
 * Created by Han on 21/10/2016.
 */

public interface ViewDanhGia {
    void DanhGiaThanhCong();

    void DanhGiaThatBai();

    void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGias);
}
