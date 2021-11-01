/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author LaptopDT
 */
public class KhachHang {

    private String maKH;
    private String hoTen;
    private String GT;
    private String diaChi;
    private String phoneNum;

    public KhachHang() {

    }

    public KhachHang(String maKH, String hoTen, String GT, String diaChi, String phoneNum) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.GT = GT;
        this.diaChi = diaChi;
        this.phoneNum = phoneNum;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
