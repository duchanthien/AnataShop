package com.eshop.android.anata.Model.TrangDanhMucSanPham;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 04/10/2016.
 */

public class ModelSanPhamTheoDanhMuc {

    public List<SanPham> LayDanhSachSanPhamTheoMaLoai(int masp, String tenham, String tenmang, int limit) {
        List<SanPham> sanPhamList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String đuongan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String, String> hsMaloaiSP = new HashMap<>();
        hsMaloaiSP.put("maloaisp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaloaiSP);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(đuongan, attrs);
        // Add Phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setHINHLON(TrangChuActivity.SERVER+object.getString("HINHSANPHAM"));
                sanPham.setHINHNHO(TrangChuActivity.SERVER+object.getString("HINHSANPHAMNHO"));
                sanPhamList.add(sanPham);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }

}
