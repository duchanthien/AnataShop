package com.eshop.android.anata.Presenter.ChiTietSanPham;

import android.content.Context;

import com.eshop.android.anata.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.GioHang_MongMuon.ModelMongMuon;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by Han on 18/10/2016.
 */

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;
    ModelMongMuon modelMongMuon;

    public PresenterLogicChiTietSanPham() {
        modelGioHang = new ModelGioHang();
        modelMongMuon = new ModelMongMuon();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
        modelMongMuon = new ModelMongMuon();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVaChiTietTheoMASP", "CHITIETSANPHAM", masp);
        if (sanPham.getMASP() > 0) {
            String[] linkHinhanh = sanPham.getHINHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkHinhanh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = modelChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp, limit);
        if (danhGiaList.size() > 0) {
            viewChiTietSanPham.HienThiDanhGia(danhGiaList);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if (kiemtra) {
            viewChiTietSanPham.ThemGioHangThanhCong();
        } else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    @Override
    public void ThemMongMuon(SanPham sanPham, Context context) {
        modelMongMuon.MoKetNetSQL(context);
        boolean kiemtra = modelMongMuon.ThemMongMuon(sanPham);
        if (kiemtra) {
            viewChiTietSanPham.ThemMongMuonThanhCong();
        } else {
            viewChiTietSanPham.ThemMongMuonThatBai();
        }
    }

    @Override
    public void XoaMongMuon(int masp, Context context) {
        modelMongMuon.MoKetNetSQL(context);
        boolean kiemtra = modelMongMuon.XoaSanPhamMongMuon(masp);
        if (kiemtra) {
            viewChiTietSanPham.XoaMongMuonThanhCong();
        } else {
            viewChiTietSanPham.XoaMongMuonThatBai();
        }
    }

    public int DemSanPhamCoTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> phamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();

        int dem = phamList.size();

        return dem;
    }
}
