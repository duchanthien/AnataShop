package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eshop.android.anata.Model.GioHang_MongMuon.ModelGioHang;
import com.eshop.android.anata.Model.GioHang_MongMuon.ModelMongMuon;
import com.eshop.android.anata.Model.ObjectClass.ChiTietKhuyenMai;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.GioHang.PresenterLogicGioHang;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.eshop.android.anata.View.GioHang.GioHangActivity;
import com.eshop.android.anata.View.GioHang.ViewGioHang;

import java.io.ByteArrayOutputStream;
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
    boolean boolMongMuon = false;
    Bitmap bitmap;
    ByteArrayOutputStream byteArrayOutputStream;
    byte[] hinhsanphamgiohang;
    ModelMongMuon modelMongMuon;
    int giatien;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        sanPhams = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
        modelMongMuon = new ModelMongMuon();
        modelMongMuon.MoKetNetSQL(context);
    }


    public class ViewHolderGioHang extends RecyclerView.ViewHolder {

        ImageView imgHinhGioHang, imgXoaSanPhamGioHang, imgThemMongMuon;
        TextView txtTenTieuDeGioHang, txtGiaTienGioHang, txtGiamGiaGioHang;
        ImageButton imgTangSoLuongSanPham, imgGiamSoLuongSanPham;
        TextView txtSoLuongSanPham;

        public ViewHolderGioHang(View itemView) {
            super(itemView);
            imgHinhGioHang = (ImageView) itemView.findViewById(R.id.imgHinhGioHang);
            imgXoaSanPhamGioHang = (ImageView) itemView.findViewById(R.id.imgXoaSanPham);
            imgThemMongMuon = (ImageView) itemView.findViewById(R.id.imgMongMuon);
            imgTangSoLuongSanPham = (ImageButton) itemView.findViewById(R.id.imgTangSoLuongSanPham);
            imgGiamSoLuongSanPham = (ImageButton) itemView.findViewById(R.id.imgGiamSoLuongSanPham);
            txtGiaTienGioHang = (TextView) itemView.findViewById(R.id.txtGiaGioHang);
            txtTenTieuDeGioHang = (TextView) itemView.findViewById(R.id.txtTieuDeGioHang);
            txtSoLuongSanPham = (TextView) itemView.findViewById(R.id.txtSoLuongSanPham);
            txtGiamGiaGioHang = (TextView) itemView.findViewById(R.id.txtGiaGioHang);

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
        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();


        giatien = sanPham.getGIA();

        if (chiTietKhuyenMai != null) {
            int phamtramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if (phamtramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);
                holder.txtGiamGiaGioHang.setVisibility(View.VISIBLE);
                holder.txtGiamGiaGioHang.setPaintFlags(holder.txtGiaTienGioHang.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtGiamGiaGioHang.setText(gia + " VNĐ ");

                giatien = giatien * phamtramkm / 100;
            }
        }
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTienGioHang.setText(gia + " VNĐ ");


        byte[] hinhsanpham = sanPham.getHinhgiohang();
        if (hinhsanpham != null) {
            Bitmap bitmapGioHang = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
            holder.imgHinhGioHang.setImageBitmap(bitmapGioHang);
        } else {
            holder.imgHinhGioHang.setImageResource(R.drawable.backgroundplashscreen);
        }

        holder.imgXoaSanPhamGioHang.setTag(sanPham.getMASP());
        holder.imgXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //Log.d("kiemtra",(int)v.getTag()+" ");
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc chắn là muốn xóa");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModelGioHang modelGioHang = new ModelGioHang();
                        modelGioHang.MoKetNoiSQL(context);
                        modelGioHang.XoaSanPhamTrongGioHang((Integer)v.getTag());
                        sanPhams.remove(position);
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

        holder.txtSoLuongSanPham.setText(String.valueOf(sanPham.getSOLUONG()));
        holder.imgTangSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();

                if (soluong < soluongtonkho) {
                    soluong++;

                } else {
                    Toast.makeText(context, "Số lượng sản phẩm bạn mua quá số lượng có trong cưa hàng", Toast.LENGTH_SHORT).show();
                }

                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                modelGioHang.LayDanhSachSanPhamTrongGioHang();
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
                Intent refresh = new Intent(context, GioHangActivity.class);
                context.startActivity(refresh);
                ((AppCompatActivity) context).finish();


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
                modelGioHang.LayDanhSachSanPhamTrongGioHang();
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
                Intent refresh = new Intent(context, GioHangActivity.class);
                context.startActivity(refresh);
                ((AppCompatActivity) context).finish();

            }
        });
        modelMongMuon.LayDanhSachSanPhamMongMuon();
        if (ChiTietSanPhamActivity.kiemtraMongMuon) {
            holder.imgThemMongMuon.setImageDrawable(getHinhChiTiet(R.drawable.ic_favorite_black_24dp));
        } else {
            holder.imgThemMongMuon.setImageDrawable(getHinhChiTiet(R.drawable.ic_favorite_border_black_24dp));
        }
        holder.imgThemMongMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietSanPhamActivity.kiemtraMongMuon = !ChiTietSanPhamActivity.kiemtraMongMuon;
                if (ChiTietSanPhamActivity.kiemtraMongMuon) {

                    bitmap = ((BitmapDrawable) holder.imgHinhGioHang.getDrawable()).getBitmap();

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    hinhsanphamgiohang = byteArrayOutputStream.toByteArray();
                    modelMongMuon.ThemMongMuon(sanPham);
                    holder.imgThemMongMuon.setImageDrawable(getHinhChiTiet(R.drawable.ic_favorite_black_24dp));
                } else {
                    modelMongMuon.XoaSanPhamMongMuon(sanPham.getMASP());
                    holder.imgThemMongMuon.setImageDrawable(getHinhChiTiet(R.drawable.ic_favorite_border_black_24dp));
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    private Drawable getHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(context, idDrawable);
        } else {
            drawable = context.getResources().getDrawable(idDrawable);
        }
        return drawable;
    }


}
