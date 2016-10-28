package com.eshop.android.anata.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eshop.android.anata.Adapter.AdapterDanhSachKhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.KhuyenMai;
import com.eshop.android.anata.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.eshop.android.anata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Han on 31/08/2016.
 */
public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {

    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerDanhSachKhuyenMai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chuongtinhkhuyenmai, container, false);

        lnHinhKhuyenMai = (LinearLayout) view.findViewById(R.id.lnHinhKhuyenMai);
        recyclerDanhSachKhuyenMai = (RecyclerView) view.findViewById(R.id.recyclerDanhSachKhuyenmai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();

        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> list) {

        int demHinh = list.size();
        if (demHinh > 5) {
            demHinh = 5;
        } else {
            demHinh = list.size();
        }
        lnHinhKhuyenMai.removeAllViews();

        for (int i = 0; i < demHinh; i++) {
            ImageView imgView = new ImageView(getContext());
            imgView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
            layoutParams.setMargins(0, 10, 0, 10);
            imgView.setLayoutParams(layoutParams);
            Picasso.with(getContext()).load(list.get(i).getHINHKHUYENMAI()).resize(760, 220).into(imgView);
            lnHinhKhuyenMai.addView(imgView);
        }

        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai = new AdapterDanhSachKhuyenMai(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerDanhSachKhuyenMai.setLayoutManager(layoutManager);
        recyclerDanhSachKhuyenMai.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();

    }
}
