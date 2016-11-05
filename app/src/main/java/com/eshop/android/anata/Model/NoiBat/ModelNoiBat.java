package com.eshop.android.anata.Model.NoiBat;

import android.util.Log;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.NoiBat;
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
    public List<NoiBat> LayDanhSachSanPhamTheoLuotMua(String tenham, String tenmang) {
        List<NoiBat> noiBatList = new ArrayList<>();

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
            //Log.d("kiemtra",dataJSON.toString());
            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                NoiBat noiBat = new NoiBat();
                noiBat.setTENLOAISP(object.getString("TENLOAISP"));

                JSONArray arrayDanhSachSanPhamNoiBat = object.getJSONArray("DANHSACHSANPHAMNOIBAT");
                int dem = arrayDanhSachSanPhamNoiBat.length();
                List<SanPham> sanPhamList = new ArrayList<>();
                for (int j = 0; j < dem; j++) {

                    JSONObject objectSanPhamList = arrayDanhSachSanPhamNoiBat.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(objectSanPhamList.getInt("MASP"));
                    sanPham.setTENSP(objectSanPhamList.getString("TENSP"));
                    sanPham.setGIA(objectSanPhamList.getInt("GIA"));
                    sanPham.setHINHLON(TrangChuActivity.SERVER + objectSanPhamList.getString("ANHLON"));
                    sanPham.setHINHNHO(TrangChuActivity.SERVER + objectSanPhamList.getString("ANHNHO"));
                    //Log.d("sanpham",sanPham.getTENSP());
                    sanPhamList.add(sanPham);
                }
                //Log.d("kiemtra",sanPhamList.get(0).getMASP()+"");
                noiBat.setSanPhamsNoiBat(sanPhamList);
                noiBatList.add(noiBat);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noiBatList;
    }

}
