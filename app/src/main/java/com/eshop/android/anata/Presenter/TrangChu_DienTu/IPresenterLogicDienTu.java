package com.eshop.android.anata.Presenter.TrangChu_DienTu;

import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.Model.TrangChu_DienTu.ModelDienTu;
import com.eshop.android.anata.View.TrangChu.ViewDienTu;

import java.util.List;

/**
 * Created by Han on 16/09/2016.
 */
public class IPresenterLogicDienTu implements IPresenterDienTu {

    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public IPresenterLogicDienTu(ViewDienTu viewDienTu) {
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop();
        if (thuongHieuList.size() > 0 && sanPhamList.size() > 0) {
            viewDienTu.HienThiDanhSach(thuongHieuList, sanPhamList);
        }
    }
}
