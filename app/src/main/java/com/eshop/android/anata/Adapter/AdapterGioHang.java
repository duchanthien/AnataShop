package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Han on 24/10/2016.
 */

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhams;
    ModelGioHang modelGioHang;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        sanPhams = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {

        ImageView imgHinhGioHang, imgXoaSanPhamGioHang;
        TextView txtTenTieuDeGioHang, txtGiaTienGioHang;
        ImageButton imgTangSoLuongSanPham, imgGiamSoLuongSanPham;
        TextView txtSoLuongSanPham;

        public ViewHolderGioHang(View itemView) {
            super(itemView);
            imgHinhGioHang = (ImageView) itemView.findViewById(R.id.imgHinhGioHang);
            imgXoaSanPhamGioHang = (ImageView) itemView.findViewById(R.id.imgXoaSanPham);
            imgTangSoLuongSanPham = (ImageButton) itemView.findViewById(R.id.imgTangSoLuongSanPham);
            imgGiamSoLuongSanPham = (ImageButton) itemView.findViewById(R.id.imgGiamSoLuongSanPham);
            txtGiaTienGioHang = (TextView) itemView.findViewById(R.id.txtGiaGioHang);
            txtTenTieuDeGioHang = (TextView) itemView.findViewById(R.id.txtTieuDeGioHang);
            txtSoLuongSanPham = (TextView) itemView.findViewById(R.id.txtSoLuongSanPham);

        }
    }

    @Override
    public ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_giohang, parent, false);
        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhams.get(position);

        holder.txtTenTieuDeGioHang.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTienGioHang.setText(gia + " VNĐ ");

        byte[] hinhsanpham = sanPham.getHinhgiohang();
        Bitmap bitmapGioHang = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
        holder.imgHinhGioHang.setImageBitmap(bitmapGioHang);
        holder.imgXoaSanPhamGioHang.setTag(sanPham.getMASP());
        holder.imgXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("kiemtra",(int)v.getTag()+" ");
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((Integer) v.getTag());
                sanPhams.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txtSoLuongSanPham.setText(String.valueOf(sanPham.getSOLUONG()));
        holder.imgTangSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();

                if (soluong < soluongtonkho) {
                    soluong++;
                }else {
                    Toast.makeText(context,"Số lượng sản phẩm bạn mua quá số lượng có trong cưa hàng",Toast.LENGTH_SHORT).show();
                }

                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));

            }
        });
        holder.imgGiamSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());

                if (soluong > 1) {
                    soluong--;
                }
                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));

            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }


}
