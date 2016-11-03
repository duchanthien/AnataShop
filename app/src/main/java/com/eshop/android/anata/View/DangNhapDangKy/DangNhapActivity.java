package com.eshop.android.anata.View.DangNhapDangKy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.eshop.android.anata.Adapter.ViewPagerAdapterDangNhap;
import com.eshop.android.anata.R;

public class DangNhapActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapterDangNhap viewPagerAdapterDangNhap;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager = (ViewPager) findViewById(R.id.viewpagerDangNhap);
        toolbar = (Toolbar) findViewById(R.id.toolbarDangNhap);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dangnhap_dangky, menu);
        return true;
    }
}
