package com.eshop.android.anata.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Han on 07/10/2016.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener {

    int itemAnDauTien = 0;
    int tongItem = 0;
    int itemLoadTruoc = 10;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager,ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongItem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager) {
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (tongItem <= (itemAnDauTien + itemLoadTruoc)) {
           // Log.d("kiemtra", tongItem + " - " + itemAnDauTien);
            iLoadMore.LoadMore(tongItem);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
