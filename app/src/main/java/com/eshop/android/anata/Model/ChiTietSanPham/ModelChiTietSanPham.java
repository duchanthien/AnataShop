package com.eshop.android.anata.Model.ChiTietSanPham;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.ChiTietSanPham;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
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
 * Created by Han on 18/10/2016.
 */

public class ModelChiTietSanPham {

    public List<DanhGia> LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String đuongan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachDanhGiaTheoMASP");

        HashMap<String, String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaSP);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(đuongan, attrs);
        // Add Phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachDanhGia= jsonObject.getJSONArray("DANHSACHDANHGIA");
            int count = jsonArrayDanhSachDanhGia.length();
            for (int i = 0; i < count; i++) {
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhSachDanhGia.getJSONObject(i);
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));
                danhGiaList.add(danhGia);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGiaList;
    }

    public SanPham LayChiTietSanPham(String tenham, String tenmang, int masp) {
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String đuongan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String, String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON = new DownloadJSON(đuongan, attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setHINHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATH(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNV(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j = 0; j < jsonArrayThongSoKyThuat.length(); j++) {
                    JSONObject jsonObject1 = jsonArrayThongSoKyThuat.getJSONObject(j);

                    for (int k = 0; k < jsonObject1.names().length(); k++) {
                        String tenchitiet = jsonObject1.names().getString(k);
                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTenchitiet(tenchitiet);
                        chiTietSanPham.setGiatri(jsonObject1.getString(tenchitiet));
                        chiTietSanPhams.add(chiTietSanPham);
                    }

                }
                sanPham.setChiTietSanPhamList(chiTietSanPhams);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }

}
