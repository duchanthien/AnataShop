package com.eshop.android.anata.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.eshop.android.anata.ConnectInternet.DownloadJSON;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Han on 02/09/2016.
 */
public class ModelDangNhap {
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;

    public AccessToken LayTokenFBHienTai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;

            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public String LayCachedDangNhap(Context context) {
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv", "");
        return tennv;
    }

    public void CapNhatCachedDangNhap(Context context,String tennv){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennv", tennv);

        editor.commit();
    }

    public Boolean KiemTraDangNhap(Context context, String tendn, String matkhau) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        Boolean kiemtra = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KiemTraDangNhap");

        HashMap<String, String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap", tendn);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if (ketqua.equals("true")) {
                kiemtra = true;
                String tennv = jsonObject.getString("tennv");

                CapNhatCachedDangNhap(context,tennv);

            } else {
                kiemtra = false;
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

    public GoogleApiClient layGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener) {
        GoogleApiClient googleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity) context, failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return googleApiClient;
    }

    public GoogleSignInResult LayThongTinDangNhapGoogle(GoogleApiClient googleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            return opr.get();
        } else {
            return null;
        }
    }

    public void HuyTokenTracker() {
        accessTokenTracker.stopTracking();
    }
}
