package com.eshop.android.anata.View.DangNhapDangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.eshop.android.anata.Model.DangNhap_DangKy.ModelDangNhap;
import com.eshop.android.anata.Model.ObjectClass.NhanVien;
import com.eshop.android.anata.Presenter.DangKy.IPresenterLogicDangKy;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.DangNhapDangKy.ViewDangKy;
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
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import java.util.Arrays;

/**
 * Created by Han on 01/09/2016.
 */
public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, OnFocusChangeListener, OnConnectionFailedListener {

    IPresenterLogicDangKy logicDangKy;

    EditText edtHoten, edtEmail, edtMatKhau, edtNhapLaiMatKhau;
    SwitchCompat swEmailDocQuyen;
    Button btnDangKy, btnDangkyFB, btnDangkyGG;

    TextInputLayout input_edtHoten, input_edtEmail, input_edtMatKhau, input_edtNhapLaiMatKhau;
    Boolean kiemtrathongtin = false;

    CallbackManager callbackManager;
    GoogleApiClient googleApiClient;

    public static int RC_SIGNIN_GOOLGE = 123;
    ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);
        // ModelDangNhap modelDangNhap = new ModelDangNhap();
        googleApiClient = FragmentDangNhap.googleApiClient;
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


        edtHoten = (EditText) view.findViewById(R.id.edtHoTen);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) view.findViewById(R.id.edtMatkhau);
        edtNhapLaiMatKhau = (EditText) view.findViewById(R.id.edtNhapLaiMatKhau);
        swEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.switchEmailDocQuyen);
        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
        btnDangkyFB = (Button) view.findViewById(R.id.btnDangKyFB);
        btnDangkyGG = (Button) view.findViewById(R.id.btnDangKyGG);
        input_edtHoten = (TextInputLayout) view.findViewById(R.id.Input_edtHoTen);
        input_edtEmail = (TextInputLayout) view.findViewById(R.id.Input_edtEmail);
        input_edtMatKhau = (TextInputLayout) view.findViewById(R.id.Input_edtMatkhau);
        input_edtNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.Input_edtNhaplaiMatKhau);

        btnDangKy.setOnClickListener(this);
        btnDangkyFB.setOnClickListener(this);
        btnDangkyGG.setOnClickListener(this);
        edtHoten.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtNhapLaiMatKhau.setOnFocusChangeListener(this);


        logicDangKy = new IPresenterLogicDangKy(this);


        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
        edtHoten.setText("");
        edtEmail.setText("");
        edtNhapLaiMatKhau.setText("");
        edtMatKhau.setText("");
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangKy:
                btnDangKy();
                break;
            case R.id.btnDangKyFB:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangKy.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangKyGG:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(iGooglePlus, RC_SIGNIN_GOOLGE);
                showProgressDialog();
                break;

        }
    }

    String emaildocquyen = "";

    private void btnDangKy() {
        String hoten = edtHoten.getText().toString();
        String email = edtEmail.getText().toString();
        String matkhau = edtMatKhau.getText().toString();
        String nhaplaimatkhau = edtNhapLaiMatKhau.getText().toString();

        swEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emaildocquyen = isChecked + " ";
            }
        });

        if (kiemtrathongtin) {

            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaLoaiNV(2);

            logicDangKy.ThucHienDangky(nhanVien);
        } else {
            Log.d("kiemtra", "Dang ky that bai");
        }


    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.edtHoTen:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edtHoten.setErrorEnabled(true);
                        input_edtHoten.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {
                        input_edtHoten.setErrorEnabled(false);
                        input_edtHoten.setError("");
                        kiemtrathongtin = true;

                    }

                }

                break;

            case R.id.edtEmail:
                if (!hasFocus) {

                    String chuoi = ((EditText) v).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edtEmail.setErrorEnabled(true);
                        input_edtEmail.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraemail) {
                            input_edtEmail.setErrorEnabled(true);
                            input_edtEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        } else {
                            input_edtEmail.setErrorEnabled(false);
                            input_edtEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }

                }
                break;
            case R.id.edtMatkhau:

                break;
            case R.id.edtNhapLaiMatKhau:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    String matkhau = edtMatKhau.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        input_edtNhapLaiMatKhau.setErrorEnabled(true);
                        input_edtNhapLaiMatKhau.setError("Mật khẩu nhập lại không trùng khớp !");
                        kiemtrathongtin = false;
                    } else {
                        input_edtNhapLaiMatKhau.setErrorEnabled(false);
                        input_edtNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mProgressDialog.cancel();
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
}
