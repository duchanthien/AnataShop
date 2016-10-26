package com.eshop.android.anata.Model.GioHang_MongMuon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eshop.android.anata.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 25/10/2016.
 */

public class ModelMongMuon {
    SQLiteDatabase database;

    public ModelMongMuon(){

    }
    public void MoKetNetSQL(Context context) {
        DatabaseSanPham databaseSanPham = new DatabaseSanPham(context);
        database = databaseSanPham.getWritableDatabase();
    }

    public boolean ThemMongMuon(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSanPham.TB_YEUTHICH_MASP, sanPham.getMASP());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(DatabaseSanPham.TB_YEUTHICH_GIATIEN, sanPham.getGIA());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhgiohang());
        long id = database.insert(DatabaseSanPham.TB_YEUTHICH, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<SanPham> LayDanhSachSanPhamMongMuon() {
        List<SanPham> phamList = new ArrayList<>();
        String truyvan = "SELECT * FROM " + DatabaseSanPham.TB_YEUTHICH;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int masp = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_HINHANH));
            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhanh);
            phamList.add(sanPham);
            cursor.moveToNext();
        }
        return phamList;
    }

    public boolean XoaSanPhamMongMuon(int masp) {
        int id = database.delete(DatabaseSanPham.TB_YEUTHICH, DatabaseSanPham.TB_YEUTHICH_MASP + " = " + masp, null);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }
}
