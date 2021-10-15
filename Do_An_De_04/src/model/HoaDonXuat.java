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
public class HoaDonXuat {

    private int maHD;
    private static int sId = 1;
    private List<KhachHang> customer;
    private Date ngayLapHoaDon;
    private List<NhanVien> nhanvien;
    private String phuongthucTT;

    public HoaDonXuat() {
        this.maHD = sId++;
    }

    public HoaDonXuat(List<KhachHang> customer, Date ngayLapHoaDon, List<NhanVien> nhanvien, String phuongthucTT) {
        this.maHD = sId++;
        this.customer = customer;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.nhanvien = nhanvien;
        this.phuongthucTT = phuongthucTT;
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
        HoaDonXuat.sId = sId;
    }

    public List<KhachHang> getCustomer() {
        return customer;
    }

    public void setCustomer(List<KhachHang> customer) {
        this.customer = customer;
    }

    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public List<NhanVien> getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(List<NhanVien> nhanvien) {
        this.nhanvien = nhanvien;
    }

    public String getPhuongthucTT() {
        return phuongthucTT;
    }

    public void setPhuongthucTT(String phuongthucTT) {
        this.phuongthucTT = phuongthucTT;
    }

}
