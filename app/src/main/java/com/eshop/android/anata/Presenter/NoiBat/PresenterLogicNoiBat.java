package com.eshop.android.anata.Presenter.NoiBat;

import com.eshop.android.anata.Model.NoiBat.ModelNoiBat;
import com.eshop.android.anata.Model.ObjectClass.NoiBat;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.View.TrangChu.Fragment.ViewNoiBat;

import java.util.List;

/**
 * Created by Han on 03/11/2016.
 */

public class PresenterLogicNoiBat implements IPresenterNoiBat {

    ViewNoiBat viewNoiBat;
    ModelNoiBat modelNoiBat;

    public PresenterLogicNoiBat(ViewNoiBat viewNoiBat) {
        this.viewNoiBat = viewNoiBat;
        modelNoiBat = new ModelNoiBat();
    }

    @Override
    public void LayDanhSachNoiBat() {
        List<NoiBat> noiBatList = modelNoiBat.LayDanhSachSanPhamTheoLuotMua("LayDanhSachSanPhamNoiBat", "DANHSACHNOIBAT");
        if (noiBatList.size() > 0) {
            viewNoiBat.HienThiDanhSachSanPhamNoiBat(noiBatList);
        }

    }
}
