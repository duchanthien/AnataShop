package com.eshop.android.anata.View.TimKiem;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.eshop.android.anata.Adapter.AdapterTopDienThoaiDienTu;
import com.eshop.android.anata.Model.ObjectClass.ILoadMore;
import com.eshop.android.anata.Model.ObjectClass.LoadMoreScroll;
import com.eshop.android.anata.Model.ObjectClass.SanPham;
import com.eshop.android.anata.Presenter.TimKiem.PresenterLogicTimKiem;
import com.eshop.android.anata.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, ILoadMore ,OnQueryTextListener{
    Toolbar toolbar;
    RecyclerView recyclerTimKiem;

    PresenterLogicTimKiem presenterLogicTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerTimKiem = (RecyclerView) findViewById(R.id.recyclerTimKiem);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem, menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(this, R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhamList);
        recyclerTimKiem.setLayoutManager(layoutManager);
        recyclerTimKiem.setAdapter(adapterTopDienThoaiDienTu);
        recyclerTimKiem.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();

    }

    @Override
    public void TimKiemThatBai() {
        recyclerTimKiem.removeAllViews();
    }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSP(query,0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
