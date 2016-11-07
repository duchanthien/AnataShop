package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelMongMuon;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.ChiTietSanPham.ChiTietSanPhamActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Han on 25/10/2016.
 */

public class AdapterMongMuon extends RecyclerView.Adapter<AdapterMongMuon.ViewHolderMongMuon> {

    Context context;
    List<SanPham> sanPhamList;

    public AdapterMongMuon(Context context, List<SanPham> list) {
        this.context = context;
        sanPhamList = list;

    }

    public class ViewHolderMongMuon extends RecyclerView.ViewHolder {
        ImageView imgHinhSanPham, imgXoaSanPham;
        TextView txtTenSanPham, txtGiaSanPham;
        CardView cardView;

        public ViewHolderMongMuon(View itemView) {
            super(itemView);
            imgHinhSanPham = (ImageView) itemView.findViewById(R.id.imgHinhMongMuon);
            imgXoaSanPham = (ImageView) itemView.findViewById(R.id.imgXoaSanPham);
            txtTenSanPham = (TextView) itemView.findViewById(R.id.txtTieuDeMongMuon);
            txtGiaSanPham = (TextView) itemView.findViewById(R.id.txtGiaMongMuon);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public ViewHolderMongMuon onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_mongmuon, parent, false);
        ViewHolderMongMuon viewHolderMongMuon = new ViewHolderMongMuon(view);
        return viewHolderMongMuon;
    }

    @Override
    public void onBindViewHolder(ViewHolderMongMuon holder, final int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.txtTenSanPham.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaSanPham.setText(gia + " VNĐ ");
        byte[] hinhsanpham = sanPham.getHinhgiohang();
        Bitmap bitmapMongMuon = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
        holder.imgHinhSanPham.setImageBitmap(bitmapMongMuon);
        holder.imgXoaSanPham.setTag(sanPham.getMASP());
        holder.imgXoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc chắn là muốn xóa");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModelMongMuon modelMongMuon = new ModelMongMuon();
                        modelMongMuon.MoKetNetSQL(context);
                        modelMongMuon.XoaSanPhamMongMuon((Integer) v.getTag());
                        sanPhamList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });
        holder.cardView.setTag(sanPham.getMASP());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", (int) v.getTag());
                context.startActivity(iChiTietSanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
