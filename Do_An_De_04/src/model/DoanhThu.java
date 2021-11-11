/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author LaptopDT
 */
public class DoanhThu {

    private String MaHD;
    private Date NgayDat;
    private int TongTienThu;
    private int TienNhan;
    private int TienDu;
    private LocalDateTime Ngay_GioBan;

    public DoanhThu() {
    }

    public DoanhThu(String MaHD, Date NgayDat, int TongTienThu, int TienNhan, int TienDu) {
        this.MaHD = MaHD;
        this.NgayDat = NgayDat;
        this.TongTienThu = TongTienThu;
        this.TienNhan = TienNhan;
        this.TienDu = TienDu;
    }

    public DoanhThu(String MaHD, int TongTienThu, int TienNhan, int TienDu) {
        this.MaHD = MaHD;
        this.TongTienThu = TongTienThu;
        this.TienNhan = TienNhan;
        this.TienDu = TienDu;
    }

    public DoanhThu(String MaHD, Date NgayDat, int TongTienThu, int TienNhan) {
        this.MaHD = MaHD;
        this.NgayDat = NgayDat;
        this.TongTienThu = TongTienThu;
        this.TienNhan = TienNhan;
    }

    public int getTienDu() {
        return TienDu;
    }

    public void setTienDu(int TienDu) {
        this.TienDu = TienDu;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(Date NgayDat) {
        this.NgayDat = NgayDat;
    }

    public int getTongTienThu() {
        return TongTienThu;
    }

    public void setTongTienThu(int TongTienThu) {
        this.TongTienThu = TongTienThu;
    }

    public int getTienNhan() {
        return TienNhan;
    }

    public void setTienNhan(int TienNhan) {
        this.TienNhan = TienNhan;
    }

    public LocalDateTime getNgay_GioBan() {
        return Ngay_GioBan;
    }

    public void setNgay_GioBan(LocalDateTime Ngay_GioBan) {
        this.Ngay_GioBan = Ngay_GioBan;
    }

}
