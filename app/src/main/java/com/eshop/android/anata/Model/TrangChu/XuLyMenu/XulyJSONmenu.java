package com.eshop.android.anata.Model.TrangChu.XuLyMenu;

import android.os.Bundle;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.LoaiSanPham;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 31/08/2016.
 */
public class XulyJSONmenu {
    String tennguoidung;
    public List<LoaiSanPham> ParserJSONMenu(String dulieujson) {
        List<LoaiSanPham> loaiSanPhams = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for (int i = 0; i < count; i++) {
                JSONObject values = loaisanpham.getJSONObject(i);
                LoaiSanPham dataloaiSanPham = new LoaiSanPham();
                dataloaiSanPham.setMALOAISP(Integer.parseInt(values.getString("MALOAISP")));
                dataloaiSanPham.setMALOAICHA(Integer.parseInt(values.getString("MALOAI_CHA")));
                dataloaiSanPham.setTENLOAISP(values.getString("TENLOAISP"));

                loaiSanPhams.add(dataloaiSanPham);
            }
        } catch (Exception e) {

        }
        return loaiSanPhams;
    }

    public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp) {
        List<LoaiSanPham> loaiSanPhams = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;
        HashMap<String, String> HSham = new HashMap<>();
        HSham.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hsMaloaiCha = new HashMap<>();
        hsMaloaiCha.put("maloaicha", String.valueOf(maloaisp));


        attrs.add(hsMaloaiCha);
        attrs.add(HSham);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        try {

            dataJSON = downloadJSON.get();
            XulyJSONmenu xulyJSONmenu = new XulyJSONmenu();
            loaiSanPhams = xulyJSONmenu.ParserJSONMenu(dataJSON);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  loaiSanPhams;
    }


}
