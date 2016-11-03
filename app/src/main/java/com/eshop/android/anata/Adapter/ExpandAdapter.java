package com.eshop.android.anata.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.eshop.android.anata.Model.ObjectClass.LoaiSanPham;
import com.eshop.android.anata.Model.TrangChu.XuLyMenu.XulyJSONmenu;
import com.eshop.android.anata.R;
import com.eshop.android.anata.View.TrangDanhMucSanPham.SanPhamTheoDanhMucFragment;

import java.util.List;

/**
 * Created by Han on 31/08/2016.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhamList;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhamList) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;
        XulyJSONmenu xulyJSONmenu = new XulyJSONmenu();

        int count = loaiSanPhamList.size();
        for (int i = 0; i < count; i++) {
            int maloaisp = loaiSanPhamList.get(i).getMALOAISP();
            loaiSanPhamList.get(i).setListCon(xulyJSONmenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }

    }


    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if (loaiSanPhamList.get(vitriGroupCha).getListCon().size() != 0) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhamList.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhamList.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhamList.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhamList.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu {
        TextView txtTenLoaiSp;
        ImageView imageView;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewGroupCha = view;
        ViewHolderMenu viewHolderMenu;
        if (viewGroupCha == null) {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = inflater.inflate(R.layout.custom_layout_group_cha, viewGroup, false);

            viewHolderMenu.txtTenLoaiSp = (TextView) viewGroupCha.findViewById(R.id.txtTenLoaiSP);
            viewHolderMenu.imageView = (ImageView) viewGroupCha.findViewById(R.id.imMenuPlus);
            viewGroupCha.setTag(viewHolderMenu);
        } else {
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }


        viewHolderMenu.txtTenLoaiSp.setText(loaiSanPhamList.get(vitriGroupCha).getTENLOAISP());

        int demLoaiSanphamCon = loaiSanPhamList.get(vitriGroupCha).getListCon().size();
        if (demLoaiSanphamCon > 0) {
            viewHolderMenu.imageView.setVisibility(View.VISIBLE);
        } else {
            viewHolderMenu.imageView.setVisibility(View.INVISIBLE);
        }

        if (isExpanded) {
            viewHolderMenu.imageView.setImageResource(R.drawable.ic_remove_black_24dp);
            viewGroupCha.setBackgroundColor(Color.LTGRAY);
        } else {
            viewHolderMenu.imageView.setImageResource(R.drawable.ic_add_black_24dp);
            viewGroupCha.setBackgroundColor(Color.WHITE);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Log.d("MaloaiSP", loaiSanPhamList.get(vitriGroupCha).getTENLOAISP() + " - " + loaiSanPhamList.get(vitriGroupCha).getMALOAISP());

                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SanPhamTheoDanhMucFragment sanPhamTheoDanhMucFragment = new SanPhamTheoDanhMucFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("MALOAITH", loaiSanPhamList.get(vitriGroupCha).getMALOAISP());
                bundle.putBoolean("KIEMTRA", false);
                bundle.putString("TENLOAITH", loaiSanPhamList.get(vitriGroupCha).getTENLOAISP());

                sanPhamTheoDanhMucFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.replace(R.id.themFragment,sanPhamTheoDanhMucFragment);
                fragmentTransaction.commit();

                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {

        /* LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCon = inflater.inflate(R.layout.custom_layout_group_con, viewGroup, false);
        ExpandableListView expandableListView = (ExpandableListView) viewGroupCon.findViewById(R.id.epMenuCon);*/

        SecondExpandable secondExpandable = new SecondExpandable(context);
        ExpandAdapter adapter = new ExpandAdapter(context, loaiSanPhamList.get(vitriGroupCha).getListCon());
        secondExpandable.setAdapter(adapter);

        secondExpandable.setGroupIndicator(null);
        adapter.notifyDataSetChanged();

        return secondExpandable;
    }

    public class SecondExpandable extends ExpandableListView {

        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

            Log.d("size", width + " - " + height + "");

            // widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


}
