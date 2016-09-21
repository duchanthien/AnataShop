package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eshop.android.anata.Model.ObjectClass.DienTu;
import com.eshop.android.anata.R;

import java.util.List;

/**
 * Created by Han on 16/09/2016.
 */
public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolderDienTu> {

    Context context;
    List<DienTu> dienTuList;

    public AdapterDienTu(Context context, List<DienTu> dienTus) {
        this.context = context;
        dienTuList = dienTus;
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imgHinhKhuyenMaiDienTu;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;

        public ViewHolderDienTu(View itemView) {
            super(itemView);
            recyclerViewThuongHieuLon = (RecyclerView) itemView.findViewById(R.id.recylerThuonghieulon);
            recyclerViewTopSanPham = (RecyclerView) itemView.findViewById(R.id.recylerTopDienTMayTB);
            imgHinhKhuyenMaiDienTu = (ImageView) itemView.findViewById(R.id.imgKhuyenMaiDienTu);

        }
    }

    @Override
    public ViewHolderDienTu onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recyclerview_dientu, parent, false);

        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);

        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienTu holder, int position) {
        DienTu dienTu = dienTuList.get(position);

        // Hiển thị danh sách Thương hiệu lớn ở recyclerview thương hiệu lớn
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, dienTu.getThuongHieus());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        // Hiển thị danh sách Thương hiệu lớn ở reyclerview thương hiệu lớn
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context,dienTu.getSanPhams());

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        holder.recyclerViewTopSanPham.setLayoutManager(layoutManager1);
        holder.recyclerViewTopSanPham.setAdapter(adapterTopDienThoaiDienTu);
        adapterTopDienThoaiDienTu .notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

}
