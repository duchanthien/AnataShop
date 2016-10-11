package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eshop.android.anata.Model.ObjectClass.ThuongHieu;
import com.eshop.android.anata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Han on 02/10/2016.
 */

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderThuongHieuLon> {

    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        thuongHieuList = thuongHieus;
    }

    public class ViewHolderThuongHieuLon extends RecyclerView.ViewHolder {

        ImageView imgLogoThuongHieuLon;

        public ViewHolderThuongHieuLon(View itemView) {
            super(itemView);
            imgLogoThuongHieuLon = (ImageView)itemView.findViewById(R.id.imgHinhTopThuongHieuLon);

        }
    }

    @Override
    public ViewHolderThuongHieuLon onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_topthuonghieulon_dientu,parent,false);
        ViewHolderThuongHieuLon viewHolderThuongHieuLon = new ViewHolderThuongHieuLon(view);

        return viewHolderThuongHieuLon;
    }

    @Override
    public void onBindViewHolder(ViewHolderThuongHieuLon holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);
        Picasso.with(context).load(thuongHieu.getHINHTH()).resize(180,90).centerInside().into(holder.imgLogoThuongHieuLon);

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


}
