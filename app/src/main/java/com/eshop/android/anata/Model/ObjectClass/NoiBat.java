package com.eshop.android.anata.Model.ObjectClass;

import java.util.List;

/**
 * Created by Han on 03/11/2016.
 */

public class NoiBat {
    String TENLOAISP;
    List<SanPham> sanPhamsNoiBat;

    public NoiBat(){

    }

    public NoiBat(String TENLOAISP, List<SanPham> sanPhamsNoiBat) {
        this.TENLOAISP = TENLOAISP;
        this.sanPhamsNoiBat = sanPhamsNoiBat;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<SanPham> getSanPhamsNoiBat() {
        return sanPhamsNoiBat;
    }

    public void setSanPhamsNoiBat(List<SanPham> sanPhamsNoiBat) {
        this.sanPhamsNoiBat = sanPhamsNoiBat;
    }
}
