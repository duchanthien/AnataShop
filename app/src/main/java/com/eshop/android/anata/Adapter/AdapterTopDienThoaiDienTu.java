package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.ChiTietKhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.ChiTietSanPham.ChiTietSanPhamActivity;
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
    int layout;

    public AdapterTopDienThoaiDienTu(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView txtTenSP, txtGiaTien, txtGiamGia;
        ProgressBar progressBarTopDienThoai;
        CardView cardView;

        public ViewHolderTopDienThoai(View itemView) {
            super(itemView);
            imgHinhSP = (ImageView) itemView.findViewById(R.id.imgTopDienThoaiDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtDienTuGiamGia);
            progressBarTopDienThoai = (ProgressBar) itemView.findViewById(R.id.progressTopDienThoai);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTopDT);


        }
    }

    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);
        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopDienThoai holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getHINHLON()).resize(140, 140).centerCrop().into(holder.imgHinhSP);
        holder.txtTenSP.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();

        int giatien = sanPham.getGIA();

        if (chiTietKhuyenMai != null) {
            int phamtramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if (phamtramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);
                holder.txtGiamGia.setVisibility(View.VISIBLE);
                holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtGiamGia.setText(gia + " VNĐ ");

                giatien = giatien * phamtramkm / 100;
            }
        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VNĐ ");
        holder.cardView.setTag(sanPham.getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChitietSanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietSanpham.putExtra("masp", (int) v.getTag());
                context.startActivity(iChitietSanpham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
