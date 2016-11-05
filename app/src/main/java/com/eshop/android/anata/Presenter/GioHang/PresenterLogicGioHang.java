package com.eshop.android.anata.Presenter.GioHang;

import android.content.Context;
import android.widget.TextView;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.View.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by Han on 24/10/2016.
 */

public class PresenterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;

    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if (sanPhamList.size() > 0) {
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }

    @Override
    public int DemSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        int dem = modelGioHang.LayDanhSachSanPhamTrongGioHang().size();

        return dem;
    }

    @Override
    public void LayTongTienTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        int tongtien, soluong = 0, gia = 0;
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if (sanPhamList.size() > 0) {
            for (int i = 0; i < sanPhamList.size(); i++) {
                soluong += sanPhamList.get(i).getSOLUONG();
                gia = sanPhamList.get(i).getGIA();
            }
            tongtien = (soluong * gia);
            viewGioHang.HienThiTongTienTrongGioHang(tongtien);

        }



    }
}
