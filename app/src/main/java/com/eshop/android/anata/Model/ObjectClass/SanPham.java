package com.eshop.android.anata.Model.ObjectClass;

import java.util.List;

/**
 * Created by Han on 13/09/2016.
 */
public class SanPham {
    int MASP, GIA, SOLUONG, MALOAISP, MATH, MANV, LUOTMUA;
    String TENSP, HINHLON, HINHNHO, THONGTIN;
    List<ChiTietSanPham> chiTietSanPhamList;

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMATH() {
        return MATH;
    }

    public void setMATH(int MATH) {
        this.MATH = MATH;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public int getLUOTMUA() {
        return LUOTMUA;
    }

    public void setLUOTMUA(int LUOTMUA) {
        this.LUOTMUA = LUOTMUA;
    }

    public String getHINHLON() {
        return HINHLON;
    }

    public void setHINHLON(String HINHLON) {
        this.HINHLON = HINHLON;
    }

    public String getHINHNHO() {
        return HINHNHO;
    }

    public void setHINHNHO(String HINHNHO) {
        this.HINHNHO = HINHNHO;
    }

    public String getTHONGTIN() {
        return THONGTIN;
    }

    public void setTHONGTIN(String THONGTIN) {
        this.THONGTIN = THONGTIN;
    }
}
