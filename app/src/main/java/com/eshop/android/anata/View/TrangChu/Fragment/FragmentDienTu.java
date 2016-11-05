package com.eshop.android.anata.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eshop.android.anata.Adapter.AdapterDienTu;
import com.eshop.android.anata.Adapter.AdapterThuongHieuLon;
import com.eshop.android.anata.Adapter.AdapterThuongHieuLonDienTu;
import com.eshop.android.anata.Adapter.AdapterTopDienThoaiDienTu;
import com.eshop.android.anata.Model.ObjectClass.DienTu;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.Presenter.TrangChu_DienTu.IPresenterLogicDienTu;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Han on 31/08/2016.
 */
public class FragmentDienTu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView, recyclerViewTopThuonghieu, recyclerViewHangMoiVe;
    List<DienTu> dienTuList;
    IPresenterLogicDienTu presenterLogicDienTu;
    ImageView imgSanPham1, imgSanPham2, imgSanPham3;

    TextView txtSanPham1, txtSanPham2, txtSanPham3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_dientu);
        recyclerViewTopThuonghieu = (RecyclerView) view.findViewById(R.id.rcv_topthuonghieulon);
        recyclerViewHangMoiVe = (RecyclerView) view.findViewById(R.id.rcv_hangmoive);
        imgSanPham1 = (ImageView) view.findViewById(R.id.imgSanPham1);
        imgSanPham2 = (ImageView) view.findViewById(R.id.imgSanPham2);
        imgSanPham3 = (ImageView) view.findViewById(R.id.imgSanPham3);
        txtSanPham1 = (TextView) view.findViewById(R.id.tvSanPham1);
        txtSanPham2 = (TextView) view.findViewById(R.id.tvSanPham2);
        txtSanPham3 = (TextView) view.findViewById(R.id.tvSanPham3);

        presenterLogicDienTu = new IPresenterLogicDienTu(this);
        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();
        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTuList) {

        this.dienTuList = dienTuList;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getActivity(), dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();
    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(), thuongHieus);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewTopThuonghieu.setLayoutManager(layoutManager);
        recyclerViewTopThuonghieu.setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_topdienthoaimaytinhbang, sanPhams);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHangMoiVe.setLayoutManager(layoutManager);
        recyclerViewHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();

        Random random = new Random();

        int vitri = random.nextInt(sanPhams.size());

        Picasso.with(getContext()).load(sanPhams.get(vitri).getHINHLON()).fit().centerInside().into(imgSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri).getTENSP()+"");

        int vitri2 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri2).getHINHLON()).fit().centerInside().into(imgSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri2).getTENSP()+"");

        int vitri3 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri3).getHINHLON()).fit().centerInside().into(imgSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri3).getTENSP()+"");
        Log.d("kiemtra",sanPhams.get(vitri3).getTENSP());

    }
}
