package com.eshop.android.anata.Model.DanhGia;

import android.util.Log;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.Model.ObjectClass.NhanVien;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 21/10/2016.
 */

public class ModelDanhGia {

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

    public Boolean ThemDanhGia(DanhGia danhGia){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDanhGia");


        HashMap<String, String> hsMDG = new HashMap<>();
        hsMDG.put("madg",danhGia.getMADG());

        HashMap<String, String> hsMASP = new HashMap<>();
        hsMASP.put("masp", String.valueOf(danhGia.getMASP()));


        HashMap<String, String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());


        HashMap<String, String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTIEUDE());

        HashMap<String, String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String, String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        attrs.add(hsHam);
        attrs.add(hsMDG);
        attrs.add(hsMASP);
        attrs.add(hsTenThietBi);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            if(ketqua.equals("true")){
                kiemtra =  true;
            }else{
                kiemtra =  false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}
