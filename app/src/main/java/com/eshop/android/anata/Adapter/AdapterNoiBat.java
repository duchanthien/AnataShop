package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.R;

import java.util.List;

/**
 * Created by Han on 07/09/2016.
 */
public class AdapterNoiBat extends RecyclerView.Adapter<AdapterNoiBat.ViewHolder> {

    Context context;
    List<SanPham> mDataset;

    public AdapterNoiBat(Context context, List<SanPham> mDataset) {
        this.context = context;
        this.mDataset = mDataset;
    }


    //chaỵ đầu tiền
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recylerview_noibat, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    //chạy thứ 2
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTieuDeNoiBat;
        RecyclerView recyclerNoiBat;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTieuDeNoiBat = (TextView) itemView.findViewById(R.id.txtTieuDeNoiBat);
            recyclerNoiBat = (RecyclerView) itemView.findViewById(R.id.recyclerItemNoiBat);
        }
    }

    //chạy thứ 3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham sanPham = mDataset.get(position);

        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context,R.layout.custom_layout_topdienthoaimaytinhbang,mDataset);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerNoiBat.setLayoutManager(layoutManager);
        holder.recyclerNoiBat.setAdapter(adapterTopDienThoaiDienTu);


    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
