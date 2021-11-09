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
public class DatHang {

    private String maHD;
    private String maKH;
    private String tenKH;
    private Date ngayLapHoaDon;
    private String maSP;
    private String tenSP;
    private int soLuongMua;
    private int TongTien;

    public DatHang() {

    }

    public DatHang(String maHD, String maKH, Date ngayLapHoaDon, String maSP, int soLuongMua, int TongTien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
        this.TongTien = TongTien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

}
