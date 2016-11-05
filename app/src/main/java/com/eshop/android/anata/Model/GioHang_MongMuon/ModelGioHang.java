package com.eshop.android.anata.Model.GioHang_MongMuon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eshop.android.anata.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Han on 23/10/2016.
 */

public class ModelGioHang {

    SQLiteDatabase sqLiteDatabase;

    public void MoKetNoiSQL(Context context) {
        DatabaseSanPham databaseSanPham = new DatabaseSanPham(context);
        sqLiteDatabase = databaseSanPham.getWritableDatabase();
    }

    public boolean ThemGioHang(SanPham sanPham) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSanPham.TB_GIOHANG_MASP, sanPham.getMASP());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_GIATIEN, sanPham.getGIA());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhgiohang());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_SOLUONG, sanPham.getSOLUONG());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_SOLUONGTONKHO, sanPham.getSOLUONGTONKHO());

        long id = sqLiteDatabase.insert(DatabaseSanPham.TB_GIOHANG, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<SanPham> LayDanhSachSanPhamTrongGioHang() {
        List<SanPham> sanPhamList = new ArrayList<>();
        String truyvan = "SELECT * FROM " + DatabaseSanPham.TB_GIOHANG;
        Cursor cursor = sqLiteDatabase.rawQuery(truyvan, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int masp = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_SOLUONGTONKHO));

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSOLUONGTONKHO(soluongtonkho);
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;

    }


    public boolean CapNhatSoLuongSanPhamGioHang(int masp, int soluong) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSanPham.TB_GIOHANG_SOLUONG, soluong);
        int id = sqLiteDatabase.update(DatabaseSanPham.TB_GIOHANG, contentValues, DatabaseSanPham.TB_GIOHANG_MASP + " = " + masp, null);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean XoaSanPhamTrongGioHang(int masp) {
        int kiemtra = sqLiteDatabase.delete(DatabaseSanPham.TB_GIOHANG, DatabaseSanPham.TB_GIOHANG_MASP + " = " + masp, null);
        if (kiemtra > 0) {
            return true;
        } else {
            return false;
        }

    }
}
