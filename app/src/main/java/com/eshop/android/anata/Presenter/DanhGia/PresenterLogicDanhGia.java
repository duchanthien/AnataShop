package com.eshop.android.anata.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.eshop.android.anata.Model.DanhGia.ModelDanhGia;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.View.DanhGia.ViewDanhGia;

import java.util.List;

/**
 * Created by Han on 21/10/2016.
 */

public class PresenterLogicDanhGia implements IPresenterDanhGia {

    ViewDanhGia ViewDanhGia;

    ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia(ViewDanhGia ViewDanhGia) {
        this.ViewDanhGia = ViewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if (kiemtra) {
            ViewDanhGia.DanhGiaThanhCong();
        } else {
            ViewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = modelDanhGia.LayDanhSachDanhGiaCuaSanPham(masp, limit);
        if(danhGiaList.size() > 0){
            progressBar.setVisibility(View.GONE);
            ViewDanhGia.HienThiDanhSachDanhGiaTheoSanPham(danhGiaList);

        }

    }
}
