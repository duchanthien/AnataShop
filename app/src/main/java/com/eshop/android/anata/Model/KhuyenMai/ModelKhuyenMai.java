package com.eshop.android.anata.Model.KhuyenMai;

import android.util.Log;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.ChiTietKhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.KhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 28/10/2016.
 */

public class ModelKhuyenMai {

    public List<KhuyenMai> LayDanhSachSanPhamTheoKhuyenMai(String tenham, String tenmang) {
        List<KhuyenMai> khuyenMaiList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String đuongan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(đuongan, attrs);
        // Add Phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPhamKhuyenMai = jsonObject.getJSONArray(tenmang);
            int count = jsonArrayDanhSachSanPhamKhuyenMai.length();
            for (int i = 0; i < count; i++) {
                KhuyenMai khuyenMai = new KhuyenMai();
                JSONObject object = jsonArrayDanhSachSanPhamKhuyenMai.getJSONObject(i);
                khuyenMai.setMAKM(object.getInt("MAKM"));
                khuyenMai.setTENKM(object.getString("TENKM"));
                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setHINHKHUYENMAI(TrangChuActivity.SERVER+object.getString("HINHKHUYENMAI"));
                //Log.d("kiemtra1",khuyenMai.getHINHKHUYENMAI()+"");
                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDanhSachSanPham = object.getJSONArray("DANHSACHSANPHAMKHUYENMAI");
                int demsanpham = arrayDanhSachSanPham.length();
                for (int j = 0; j < demsanpham; j++) {
                    JSONObject ObjectSanPham = arrayDanhSachSanPham.getJSONObject(j);

                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(ObjectSanPham.getInt("MASP"));
                    sanPham.setTENSP(ObjectSanPham.getString("TENSP"));
                    sanPham.setGIA(ObjectSanPham.getInt("GIA"));
                    sanPham.setHINHLON(TrangChuActivity.SERVER + ObjectSanPham.getString("ANHLON"));
                    sanPham.setHINHNHO(TrangChuActivity.SERVER+ObjectSanPham.getString("ANHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(ObjectSanPham.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                    sanPhamList.add(sanPham);
                }
                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMaiList.add(khuyenMai);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return khuyenMaiList;
    }

}
