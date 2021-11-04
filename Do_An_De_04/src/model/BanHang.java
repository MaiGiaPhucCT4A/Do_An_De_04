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
public class BanHang {

    private String maHD;
    private Date ngayLapHoaDon;
    private String maSP;
    private String tenSP;
    private int soluongMua;
    private int TongTien;

    public BanHang() {

    }

    public BanHang(String maHD, Date ngayLapHoaDon, String maSP, int soluongMua, int TongTien) {
        this.maHD = maHD;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.maSP = maSP;
        this.soluongMua = soluongMua;
        this.TongTien = TongTien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
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

    public int getSoluongMua() {
        return soluongMua;
    }

    public void setSoluongMua(int soluongMua) {
        this.soluongMua = soluongMua;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

}
