package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.KhuyenMai;
import com.eshop.android.anata.R;

import java.util.List;

/**
 * Created by Han on 28/10/2016.
 */

public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolderKhuyenMai> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMais) {
        this.context = context;
        khuyenMaiList = khuyenMais;
    }


    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {
        RecyclerView recylerItemKhuyenMai;
        TextView txtTieuDeKhuyenMai;

        public ViewHolderKhuyenMai(View itemView) {
            super(itemView);
            recylerItemKhuyenMai = (RecyclerView)itemView.findViewById(R.id.recyclerItemKhuyenMai);
            txtTieuDeKhuyenMai = (TextView)itemView.findViewById(R.id.txtTieuDeKhuyenMai);
        }
    }

    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai, parent, false);

        ViewHolderKhuyenMai viewHolderKhuyenMai = new ViewHolderKhuyenMai(view);
        return viewHolderKhuyenMai;
    }

    @Override
    public void onBindViewHolder(ViewHolderKhuyenMai holder, int position) {
        KhuyenMai khuyenMai = khuyenMaiList.get(position);
        holder.txtTieuDeKhuyenMai.setText(khuyenMai.getTENLOAISP());
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context,R.layout.custom_layout_topdienthoaimaytinhbang,khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.recylerItemKhuyenMai.setLayoutManager(layoutManager);
        holder.recylerItemKhuyenMai.setAdapter(adapterTopDienThoaiDienTu);

    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }


}
