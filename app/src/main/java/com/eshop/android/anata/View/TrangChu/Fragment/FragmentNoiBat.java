package com.eshop.android.anata.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eshop.android.anata.Adapter.AdapterNoiBat;
import com.eshop.android.anata.Model.ObjectClass.NoiBat;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.NoiBat.PresenterLogicNoiBat;
import com.eshop.android.anata.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 31/08/2016.
 */
public class FragmentNoiBat extends Fragment implements ViewNoiBat{
    AdapterNoiBat adapterNoiBat;
    RecyclerView  recyclerView;
    PresenterLogicNoiBat presenterLogicNoiBat;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerDanhSachNoiBat);
        presenterLogicNoiBat = new PresenterLogicNoiBat(this);
        presenterLogicNoiBat.LayDanhSachNoiBat();
        return view;
    }

    @Override
    public void HienThiDanhSachSanPhamNoiBat(List<NoiBat> list) {
        adapterNoiBat = new AdapterNoiBat(getContext(),list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();

    }
}
