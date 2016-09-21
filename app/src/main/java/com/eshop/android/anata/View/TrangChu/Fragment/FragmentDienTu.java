package com.eshop.android.anata.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eshop.android.anata.Adapter.AdapterDienTu;
import com.eshop.android.anata.Model.ObjectClass.DienTu;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.Presenter.TrangChu_DienTu.IPresenterLogicDienTu;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 31/08/2016.
 */
public class FragmentDienTu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView;
    List<DienTu> dienTuList;
    IPresenterLogicDienTu presenterLogicDienTu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_dientu);

        presenterLogicDienTu = new IPresenterLogicDienTu(this);

        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams) {

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieus);
        dienTu.setSanPhams(sanPhams);
        dienTuList.add(dienTu);

        AdapterDienTu adapterDienTu = new AdapterDienTu(getActivity(), dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();
    }
}
