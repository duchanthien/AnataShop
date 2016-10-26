package com.eshop.android.anata.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.eshop.android.anata.Model.ObjectClass.DanhGia;

/**
 * Created by Han on 21/10/2016.
 */

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaSanPham(int masp, int limit, ProgressBar progressBar);
}
