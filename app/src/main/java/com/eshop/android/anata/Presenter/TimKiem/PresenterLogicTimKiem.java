package com.eshop.android.anata.Presenter.TimKiem;

import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.TimKiem.ModelTimKiem;
import com.eshop.android.anata.View.TimKiem.ViewTimKiem;

import java.util.List;

/**
 * Created by Han on 29/10/2016.
 */

public class PresenterLogicTimKiem implements IPresenterTimKiem {

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamTheoTenSP(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.TimKiemSanPhamTheoTen(tensp, "TimKiemSanPhamTheoTenSP", "DANHSACHSANPHAM", limit);
        if (sanPhamList.size() > 0) {
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        } else {
            viewTimKiem.TimKiemThatBai();
        }
    }
}
