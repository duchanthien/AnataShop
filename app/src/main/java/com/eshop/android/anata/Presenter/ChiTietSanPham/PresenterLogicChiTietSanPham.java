package com.eshop.android.anata.Presenter.ChiTietSanPham;

import com.eshop.android.anata.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.View.ChiTietSanPham.ViewChiTietSanPham;

/**
 * Created by Han on 18/10/2016.
 */

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVaChiTietTheoMASP", "CHITIETSANPHAM", masp);
        if(sanPham.getMASP() > 0){
            String[] linkHinhanh = sanPham.getHINHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkHinhanh);
        }
    }
}
