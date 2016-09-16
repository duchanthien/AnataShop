package com.eshop.android.anata.View.DangNhapDangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

/**
 * Created by Han on 01/09/2016.
 */
public class FragmentDangNhap extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, GoogleApiClient.OnConnectionFailedListener {

    Button btnDangNhapFB, btnDangNhapGG, btnDangNhap;
    EditText edtEmail, edtMatKhau;
    TextInputLayout input_edtEmailDN, input_edtMatKhauDN;
    CallbackManager callbackManager;
    public static GoogleApiClient googleApiClient;
    public static int RC_SIGNIN_GOOLGE = 123;
    ProgressDialog mProgressDialog;
    ModelDangNhap modelDangNhap;

    Boolean kiemtradangnhap = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        modelDangNhap = new ModelDangNhap();
        googleApiClient = modelDangNhap.layGoogleApiClient(getContext(), this);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangchu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangchu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Kiemtra", "Error");
            }
        });

        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        btnDangNhapFB = (Button) view.findViewById(R.id.btnDangNhapFB);
        btnDangNhapGG = (Button) view.findViewById(R.id.btnDangNhapGG);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) view.findViewById(R.id.edtPassword);
        input_edtEmailDN = (TextInputLayout) view.findViewById(R.id.Input_edtEmailDN);
        input_edtMatKhauDN = (TextInputLayout) view.findViewById(R.id.Input_edtMatkhauDN);
        btnDangNhapFB.setOnClickListener(this);
        btnDangNhapGG.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        edtMatKhau.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);

        return view;
    }

    private void btnDangNhap() {

        String tendn = edtEmail.getText().toString();
        String matkhau = edtMatKhau.getText().toString();
        boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(), tendn, matkhau);
        if (kiemtradangnhap) {
            if (kiemtra) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            } else {
                Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangNhap:
                btnDangNhap();
                break;
            case R.id.btnDangNhapFB:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGG:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(iGooglePlus, RC_SIGNIN_GOOLGE);
                showProgressDialog();
                break;
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage("");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGNIN_GOOLGE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d("Google", result.getSignInAccount().getEmail());
            if (result.isSuccess()) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mProgressDialog.cancel();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.edtEmail:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.equals("") || chuoi.equals(null)) {
                        input_edtEmailDN.setErrorEnabled(true);
                        input_edtEmailDN.setError("Bạn chưa nhập địa chỉ Email !");
                        kiemtradangnhap = false;
                    } else {
                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraemail) {
                            input_edtEmailDN.setErrorEnabled(true);
                            input_edtEmailDN.setError("Email nhập vào chưa chính xác ! !");
                            kiemtradangnhap = false;
                        } else {
                            input_edtEmailDN.setErrorEnabled(false);
                            input_edtEmailDN.setError("");
                            kiemtradangnhap = true;
                        }
                    }
                }
                break;
            case R.id.edtPassword:

                break;
        }
    }
}
