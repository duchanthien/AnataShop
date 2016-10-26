package com.eshop.android.anata.Presenter.MongMuon;

import android.content.Context;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelMongMuon;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.View.MongMuon.ViewMongMuon;

import java.util.List;

/**
 * Created by Han on 25/10/2016.
 */

public class PresenterLogicMongMuon implements IPresenterMongMuon {

    ViewMongMuon viewMongMuon;
    ModelMongMuon modelMongMuon;

    public PresenterLogicMongMuon(ViewMongMuon viewMongMuon) {
        modelMongMuon = new ModelMongMuon();
        this.viewMongMuon = viewMongMuon;
    }

    @Override
    public void LayDanhSachSanPhamMongMuon(Context context) {
        modelMongMuon.MoKetNetSQL(context);
        List<SanPham> sanPhamList = modelMongMuon.LayDanhSachSanPhamMongMuon();
        if (sanPhamList.size() > 0) {
            viewMongMuon.HienThiDanhSachSanPhamTrongMongMuon(sanPhamList);
        }
    }


}
