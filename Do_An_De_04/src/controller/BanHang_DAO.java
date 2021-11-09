/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.BanHang;
import model.DatHang;
import model.KhachHang;
import model.SanPham;

public class BanHang_DAO {

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Do_An_De_04";
    String user = "sa";
    String password = "123456";
    Statement st;
    ResultSet rs;

    private Connection conn;

    public BanHang_DAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BanHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BanHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean AddHoaDon(BanHang BH) {
        String sql = "insert into BanHang(MaHD,MaSP,SoLuongMua,TongTien) values (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, BH.getMaHD());
            ps.setString(2, BH.getMaSP());
            ps.setInt(3, BH.getSoluongMua());
            ps.setInt(4, BH.getTongTien());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void delectHoaDonBanHang() {
        String sql = "delete from BanHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BanHang> getListBanHang() { // đọc ở bảng
        List<BanHang> listBH = new ArrayList<>();
        String sql = "select * from BanHang a,SanPham b where a.MaSP=b.MaSP";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                BanHang s = new BanHang();
                s.setMaHD(rs.getString("MaHD"));
                s.setMaSP(rs.getString("MaSP"));
                s.setTenSP(rs.getString("TenSP"));
                s.setSoluongMua(rs.getInt("SoLuongMua"));
                s.setTongTien(rs.getInt("TongTien"));
                listBH.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listBH;
    }

    public void LuuMaHoaDon_BH(BanHang HD) {
        String sql = "insert into MaBanHang(MaHD) values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, HD.getMaHD());
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BanHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BanHang> MaDonDatHang() {
        List<BanHang> listHD = new ArrayList<>();
        String sql = "select * from MaBanHang order by MaHD";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                BanHang s = new BanHang();
                s.setMaHD(rs.getString("MaHD"));
                listHD.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listHD;
    }
}
