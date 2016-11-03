package com.eshop.android.anata.Presenter.KhuyenMai;

import com.eshop.android.anata.Model.KhuyenMai.ModelKhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.KhuyenMai;
import com.eshop.android.anata.View.TrangChu.Fragment.ViewKhuyenMai;

import java.util.List;

/**
 * Created by Han on 28/10/2016.
 */

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {

    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();

    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSanPhamTheoKhuyenMai("LayDanhSachKhuyenMai", "DANHSACHKHUYENMAI");
        if (khuyenMaiList.size() > 0) {
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}
