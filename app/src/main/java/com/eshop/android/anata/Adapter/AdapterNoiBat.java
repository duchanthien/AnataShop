package com.eshop.android.anata.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eshop.android.anata.R;

import java.util.List;

/**
 * Created by Han on 07/09/2016.
 */
public class AdapterNoiBat extends RecyclerView.Adapter<AdapterNoiBat.ViewHolder> {

    Context context;
    List<String> mDataset;

    public AdapterNoiBat(Context context, List<String> mDataset) {
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

    //chạy thứ 3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mDataset.get(position));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //chạy thứ 2
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tvNoiBat);
        }
    }
}
