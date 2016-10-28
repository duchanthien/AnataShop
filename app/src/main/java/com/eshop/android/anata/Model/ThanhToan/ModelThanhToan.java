package com.eshop.android.anata.Model.ThanhToan;

import android.util.Log;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.Model.ObjectClass.ChiTietHoaDon;
import com.eshop.android.anata.Model.ObjectClass.HoaDon;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 26/10/2016.
 */

public class ModelThanhToan {

    public Boolean ThemHoaDon(HoaDon hoaDon) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemHoaDon");

        List<ChiTietHoaDon> chiTietHoaDons = hoaDon.getChiTietHoaDonList();

        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";

        for (int i = 0; i < chiTietHoaDons.size(); i++) {
            chuoijson += "{";
            chuoijson += "\"masp\" : " + chiTietHoaDons.get(i).getMASP() + ",";
            chuoijson += "\"soluong\" : " + chiTietHoaDons.get(i).getSOLUONG();
            if (i == chiTietHoaDons.size() - 1) {
                chuoijson += "}";
            } else {
                chuoijson += "},";
            }
        }
        chuoijson += "]}";

        HashMap<String, String> hsDanhSachSP = new HashMap<>();
        hsDanhSachSP.put("danhsachsanpham", chuoijson);


        HashMap<String, String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan", hoaDon.getTENNGUOINHAN());

        HashMap<String, String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt", hoaDon.getSODT());

        HashMap<String, String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", hoaDon.getDIACHI());


        HashMap<String, String> hsThanhToan = new HashMap<>();
        hsThanhToan.put("hinhthucthanhtoan", String.valueOf(hoaDon.getHINHTHUCTHANHTOAN()));

        attrs.add(hsHam);
        attrs.add(hsDanhSachSP);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
        attrs.add(hsThanhToan);

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
