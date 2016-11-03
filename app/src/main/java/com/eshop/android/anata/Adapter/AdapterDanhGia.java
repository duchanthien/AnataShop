package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.DanhGia;
import com.eshop.android.anata.R;

import java.util.List;

/**
 * Created by Han on 22/10/2016.
 */

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {

    List<DanhGia> danhGias;
    int limit;
    Context context;

    public AdapterDanhGia( Context context,List<DanhGia> danhGiaList, int limit) {
        danhGias = danhGiaList;
        this.limit = limit;
        this.context = context;
    }


    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {

        TextView txtTieuDeDanhGia, txtDuocDanhGiaBoi, txtNoiDungDanhGia;
        RatingBar ratingBar;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);
            txtTieuDeDanhGia = (TextView) itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNoiDungDanhGia = (TextView) itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtDuocDanhGiaBoi = (TextView) itemView.findViewById(R.id.txtDuocDangBoi);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rbDangGia);

        }
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_danhgia_chitiet, parent, false);
        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhGia holder, int position) {

        DanhGia danhGia = danhGias.get(position);
        holder.txtTieuDeDanhGia.setText(danhGia.getTIEUDE());
        holder.txtNoiDungDanhGia.setText(danhGia.getNOIDUNG());
        holder.txtDuocDanhGiaBoi.setText("Được đánh giá bởi " + danhGia.getTENNGUOIDANHGIA() + " ngày " + danhGia.getNGAYDANHGIA());
        holder.ratingBar.setRating(danhGia.getSOSAO());

    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if (danhGias.size() < limit) {
            dong = danhGias.size();
        } else {
            if (limit != 0) {
                dong = limit;
            } else {
                dong = danhGias.size();

            }
        }

        return dong;
    }


}
