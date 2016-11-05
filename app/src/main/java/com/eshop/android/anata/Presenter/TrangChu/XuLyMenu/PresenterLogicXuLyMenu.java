package com.eshop.android.anata.Presenter.TrangChu.XuLyMenu;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
import com.eshop.android.anata.Model.ObjectClass.LoaiSanPham;
import com.eshop.android.anata.Model.TrangChu.XuLyMenu.XulyJSONmenu;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
import com.eshop.android.anata.View.TrangChu.ViewXuLyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 31/08/2016.
 */
public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    ViewXuLyMenu viewXuLyMenu;
    public  PresenterLogicXuLyMenu(){

    }
    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        // ip 192.168.80.1:8080
        // ip emulator 10.0.2.15
        // ip 127.0.0.1
        List<LoaiSanPham> loaiSanPhamList;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";
        // Lấy bằng phương thức GET
        // String duongdan = "http://192.168.80.1:8080/weblazada/loaisanpham.php?maloaicha=0";
        // DownloadJSON downloadJSON = new DownloadJSON(duongdan);

        // end GET

        // Lấy bằng phương thức POST

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String, String> hsMaloaiCha = new HashMap<>();
        hsMaloaiCha.put("maloaicha","0");

        attrs.add(hsMaloaiCha);
        attrs.add(hsHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);

        // end post
        downloadJSON.execute();
        try {

            dataJSON = downloadJSON.get();
            XulyJSONmenu xulyJSONmenu = new XulyJSONmenu();
            loaiSanPhamList = xulyJSONmenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    String tennguoidung = "";
    @Override
    public AccessToken LayTokenNguoiDungFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFBHienTai();
        return  accessToken;
    }
}
