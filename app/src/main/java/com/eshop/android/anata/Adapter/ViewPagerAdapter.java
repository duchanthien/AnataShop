package com.eshop.android.anata.Adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.eshop.android.anata.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentDienTu;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentLamDep;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentMeVaBe;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentNoiBat;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentThoiTrang;
import com.eshop.android.anata.View.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 31/08/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleFragment.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
