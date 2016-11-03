package com.eshop.android.anata.Model.NoiBat;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
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
 * Created by Han on 03/11/2016.
 */

public class ModelNoiBat {
    public List<SanPham> LayDanhSachSanPhamTheoLuotMua(String tenham, String tenmang) {
        List<SanPham> sanPhamList = new ArrayList<>();

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
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIA"));
                sanPham.setHINHLON(object.getString("ANHLON"));
                sanPham.setHINHNHO(object.getString("ANHNHO"));
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
