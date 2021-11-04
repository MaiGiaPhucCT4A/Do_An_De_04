/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author LaptopDT
 */
public class HoaDon {

    private int maHD;
    private static int sId = 1;
    private String maKH;
    private Date ngayLapHoaDon;
    private String maSP;
    private int TongTien;

    public HoaDon() {
        this.maHD = sId++;
    }

    public HoaDon(String maKH, Date ngayLapHoaDon, String maSP, int TongTien) {
        this.maHD = sId++;
        this.maKH = maKH;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.maSP = maSP;
        this.TongTien = TongTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public static int getsId() {
        return sId;
    }

    public static void setsId(int sId) {
        HoaDon.sId = sId;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

}
