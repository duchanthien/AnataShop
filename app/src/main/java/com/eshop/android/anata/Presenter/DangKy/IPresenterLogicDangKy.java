package com.eshop.android.anata.Presenter.DangKy;

import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangKy;
import com.eshop.android.anata.Model.ObjectClass.NhanVien;
import com.eshop.android.anata.View.DangNhapDangKy.ViewDangKy;

/**
 * Created by Han on 03/09/2016.
 */
public class IPresenterLogicDangKy implements IPresenterDangKy {

    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public IPresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }


    @Override
    public void ThucHienDangky(NhanVien nhanVien) {
        boolean kiemtra = modelDangKy.DangKyThanhVien(nhanVien);
        if (kiemtra) {
            this.viewDangKy.DangKyThanhCong();
        } else {
            this.viewDangKy.DangKyThatBai();
        }
    }
}
