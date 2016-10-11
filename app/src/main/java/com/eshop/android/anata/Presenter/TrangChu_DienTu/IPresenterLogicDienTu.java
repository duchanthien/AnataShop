package com.eshop.android.anata.Presenter.TrangChu_DienTu;

import com.eshop.android.anata.Model.ObjectClass.DienTu;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.Model.TrangChu_DienTu.ModelDienTu;
import com.eshop.android.anata.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
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
        List<DienTu> dienTuList = new ArrayList<>();


        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopDienThoaiVaMayTinhBang","TOPDIENTHOAIVAMAYTINHBANG");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTennoibat("Danh sách các thương hiệu lớn");
        dienTu.setTentopnoibat("Top Điện thoại và Máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTuList.add(dienTu);

        List<ThuongHieu> phukienList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");
        List<SanPham> topphukienList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopPhuKien","TOPPHUKIEN");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(phukienList);
        dienTu2.setSanPhams(topphukienList);
        dienTu2.setTennoibat("Phụ kiện");
        dienTu2.setTentopnoibat("Top Phụ kiện");
        dienTu2.setThuonghieu(false);
        dienTuList.add(dienTu2);

        List<ThuongHieu> tienichList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");
        List<SanPham> toptienichList = modelDienTu.LayDanhSachSanPhamTop("LayTopTienIch","TOPTIENICH");

        DienTu dienTu3 = new DienTu();
        dienTu3.setThuongHieus(tienichList);
        dienTu3.setSanPhams(toptienichList);
        dienTu3.setTennoibat("Tiện ích");
        dienTu3.setTentopnoibat("Top Video & Tivi");
        dienTu3.setThuonghieu(false);
        dienTuList.add(dienTu3);

        if (thuongHieuList.size() > 0 && sanPhamList.size() > 0) {
            viewDienTu.HienThiDanhSach(dienTuList);
        }
    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieus = modelDienTu.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon","DANHSACHTHUONGHIEU");
        if(thuongHieus.size() > 0){
            viewDienTu.HienThiLogoThuongHieu(thuongHieus);
        }else{
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhams = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachSanPhamMoi","DANHSACHSANPHAMMOIVE");
        if(sanPhams.size() > 0){
            viewDienTu.HienThiSanPhamMoiVe(sanPhams); ;
        }else{
            viewDienTu.LoiLayDuLieu();
        }
    }
}
