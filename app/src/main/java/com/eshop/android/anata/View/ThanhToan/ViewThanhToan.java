package com.eshop.android.anata.View.ThanhToan;

import com.eshop.android.anata.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Han on 26/10/2016.
 */

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
