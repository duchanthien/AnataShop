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
import com.eshop.android.anata.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 31/08/2016.
 */
public class FragmentNoiBat extends Fragment{
    AdapterNoiBat adapterNoiBat;
    RecyclerView  recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvNoiBat);

        List<String> mData = new ArrayList<>();

        for(int i = 0 ; i < 50;i++){
            String name = "Noi bat "+i;
            mData.add(name);
        }

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(layoutManager);
        adapterNoiBat = new AdapterNoiBat(getActivity(),mData);

        recyclerView.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();
        return view;
    }
}
