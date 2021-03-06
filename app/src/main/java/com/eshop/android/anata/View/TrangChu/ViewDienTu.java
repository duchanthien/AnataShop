package com.eshop.android.anata.View.TrangChu;

import com.eshop.android.anata.Model.ObjectClass.DienTu;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Han on 16/09/2016.
 */
public interface ViewDienTu {

    void HienThiDanhSach(List<DienTu> dienTus);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);


}
