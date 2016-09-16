package com.eshop.android.anata.Model.DangNhap_DangKy;

import android.util.Log;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.NhanVien;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 03/09/2016.
 */
public class ModelDangKy {

    public Boolean DangKyThanhVien(NhanVien nv){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien");


        HashMap<String, String> hsTenNhanvien = new HashMap<>();
        hsTenNhanvien.put("tennv",nv.getTenNV());

        HashMap<String, String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",nv.getTenDN());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",nv.getMatKhau());

        HashMap<String, String> hsMaloaiNV = new HashMap<>();
        hsMaloaiNV.put("maloainv",String.valueOf(nv.getMaLoaiNV()));

        HashMap<String, String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",nv.getEmailDocQuyen());

        attrs.add(hsHam);
        attrs.add(hsTenNhanvien);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaloaiNV);
        attrs.add(hsEmailDocQuyen);

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
