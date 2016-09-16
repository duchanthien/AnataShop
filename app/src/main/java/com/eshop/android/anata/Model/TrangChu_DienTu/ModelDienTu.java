package com.eshop.android.anata.Model.TrangChu_DienTu;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
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
 * Created by Han on 16/09/2016.
 */
public class ModelDienTu {
    public List<ThuongHieu> LayDanhSachThuongHieuLon(){
        List<ThuongHieu> thuongHieuList  = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachCacThuongHieuLon");
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();


        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayThuongHieuLon = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");
            int count = jsonArrayThuongHieuLon.length();
            for(int i = 0 ; i < count; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayThuongHieuLon.getJSONObject(i);

                thuongHieu.setMATH(object.getInt("MATHUONGHIEU"));
                thuongHieu.setTENTH(object.getString("TENTHUONGHIEU"));
                thuongHieu.setHINHTH(object.getString("HINHTHUONGHIEU"));

                thuongHieuList.add(thuongHieu);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return thuongHieuList;
    }
}
