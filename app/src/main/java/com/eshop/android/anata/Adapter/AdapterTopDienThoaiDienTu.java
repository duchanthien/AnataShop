package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Han on 19/09/2016.
 */
public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolderTopDienThoai> {

    Context context;
    List<SanPham> sanPhamList;

    public AdapterTopDienThoaiDienTu(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView txtTenSP, txtGiaTien, txtGiamGia;
        ProgressBar progressBarTopDienThoai;

        public ViewHolderTopDienThoai(View itemView) {
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgTopDienThoaiDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtDienTuGiamGia);
            progressBarTopDienThoai = (ProgressBar) itemView.findViewById(R.id.progressTopDienThoai);

        }
    }

    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_topdienthoaimaytinhbang, parent, false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);
        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopDienThoai holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getHINHLON()).resize(140, 140).centerCrop().into(holder.imgHinhSP);
        holder.txtTenSP.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTien.setText(gia + " VND ");

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
