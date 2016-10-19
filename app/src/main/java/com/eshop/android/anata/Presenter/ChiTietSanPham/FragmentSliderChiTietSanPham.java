package com.eshop.android.anata.Presenter.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eshop.android.anata.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Han on 18/10/2016.
 */

public class FragmentSliderChiTietSanPham extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_slider_chitietsanpham, container, false);
        Bundle bundle = getArguments();
        String linkHinh = bundle.getString("linkhinh");

        ImageView imageView = (ImageView) view.findViewById(R.id.imgSlider);
        Picasso.with(getContext()).load(linkHinh).into(imageView);

        return view;
    }
}
