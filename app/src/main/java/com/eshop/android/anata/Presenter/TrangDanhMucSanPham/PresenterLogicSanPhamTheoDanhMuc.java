package com.eshop.android.anata.Presenter.TrangDanhMucSanPham;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.TrangDanhMucSanPham.ModelSanPhamTheoDanhMuc;
import com.eshop.android.anata.View.TrangDanhMucSanPham.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 04/10/2016.
 */

public class PresenterLogicSanPhamTheoDanhMuc implements IPresenterSanPhamTheoDanhMuc {

    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    ModelSanPhamTheoDanhMuc modelSanPhamTheoDanhMuc;
    ModelGioHang modelGioHang;

    public PresenterLogicSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        modelSanPhamTheoDanhMuc = new ModelSanPhamTheoDanhMuc();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayDanhSachSanPhamTheoMaLoai(int masp, boolean kiemtra) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM", 0);
        } else {
            sanPhamList = modelSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "LayDanhSachSanPhamTheoMaloaiDanhMuc", "DANHSACHSANPHAM", 0);
        }

        if (sanPhamList.size() > 0) {
            viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSanPham(sanPhamList);
        } else {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThiDanhSachSanPham();
        }
    }

    public List<SanPham> LayDanhSachSanPhamTheoMaLoaiLoadMore(int masp, boolean kiemtra, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM", limit);
        } else {
            sanPhamList = modelSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "LayDanhSachSanPhamTheoMaloaiDanhMuc", "DANHSACHSANPHAM", limit);
        }

        if(sanPhamList.size() != 0){
            progressBar.setVisibility(View.GONE);
        }
        return  sanPhamList;
    }
    public int DemSanPhamCoTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> phamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();

        int dem = phamList.size();

        return dem;
    }
}
