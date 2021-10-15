/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.SanPham;
//import java.sql.ResultSet;
//import java.sql.Statement;

/**
 *
 * @author LaptopDT
 */
public class SanPham_DAO {

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Do_An_De_04";
    String user = "sa";
    String password = "123456";
    Statement st;
    ResultSet rs;

    private Connection conn;

    public SanPham_DAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean addSanPham(SanPham sp) {
        String sql = "insert into SanPham(MaSP, TenSP, NhaSX, LoaiSP, SoLuong, "
                + "DonViTinh, GiaNhap, GiaBan, NSX, HSD)"
                + " values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setString(3, sp.getNhaSanXuat());
            ps.setString(4, sp.getLoaiSP());
            ps.setInt(5, sp.getSl());
            ps.setString(6, sp.getDonViTinh());
            ps.setInt(7, sp.getGiaNhap());
            ps.setInt(8, sp.getGiaBan());
            ps.setDate(9, new Date(sp.getNgaySX().getTime()));
            ps.setDate(10, new Date(sp.getHSD().getTime()));

            return ps.executeUpdate() > 0;// update data
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi: " + ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
        return false;
    }

    public void delectSanPham(String masp) {
        String sql = "delete from SanPham where MaSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masp);

            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return false;
    }

    public boolean updateSanPham(SanPham sp) {
        String sql = "update SanPham set TenSP=?, NhaSX=?, LoaiSP=?, "
                + "SoLuong=?, DonViTinh=?, GiaNhap=?, GiaBan=?, NSX=?, HSD=? "
                + "where MaSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getNhaSanXuat());
            ps.setString(3, sp.getLoaiSP());
            ps.setInt(4, sp.getSl());
            ps.setString(5, sp.getDonViTinh());
            ps.setInt(6, sp.getGiaNhap());
            ps.setInt(7, sp.getGiaBan());
            ps.setDate(8, new Date(sp.getNgaySX().getTime()));
            ps.setDate(9, new Date(sp.getHSD().getTime()));
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
        return false;
    }

    public List<SanPham> getListSanPham() { // đọc ở bảng
        List<SanPham> listSP = new ArrayList<>();
        String sql = "select * from SanPham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                SanPham s = new SanPham();
                s.setMaSP(rs.getString("MaSP"));// lấy giá trị từ db
                s.setTenSP(rs.getString("TenSP"));
                s.setNhaSanXuat(rs.getString("NhaSX"));
                s.setLoaiSP(rs.getString("LoaiSP"));
                s.setSl(rs.getInt("SoLuong"));
                s.setDonViTinh(rs.getString("DonViTinh"));
                s.setGiaNhap(rs.getInt("GiaNhap"));
                s.setGiaBan(rs.getInt("GiaBan"));
                s.setNgaySX(rs.getDate("NSX"));
                s.setHSD(rs.getDate("HSD"));

                listSP.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listSP;
    }

    public static void main(String[] args) {
        new SanPham_DAO();
    }
}
