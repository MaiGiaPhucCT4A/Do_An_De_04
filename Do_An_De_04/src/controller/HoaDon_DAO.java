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
import model.HoaDon;
import model.KhachHang;
import model.SanPham;

/**
 *
 * @author LaptopDT
 */
public class HoaDon_DAO {

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Do_An_De_04";
    String user = "sa";
    String password = "123456";
    Statement st;
    ResultSet rs;

    private Connection conn;

    public HoaDon_DAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean AddHoaDon(HoaDon HD) {
        String sql = "insert into DonDatHang(NgayDat,MaSP,MaKH,TongTien) values (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new Date(HD.getNgayLapHoaDon().getTime()));
            ps.setString(2, HD.getMaSP());
            ps.setString(3, HD.getMaKH());
            ps.setInt(4, HD.getTongTien());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    public void Update_SoLuongSP(SanPham sp, String tensp) {
//        String sql = "update SanPham set SoLuong = ? where TenSP = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, sp.getSl());
//            ps.setString(2, tensp);
//            int n = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public List<HoaDon> getListHoaDon() { // đọc ở bảng
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "select * from DonDatHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                HoaDon s = new HoaDon();
                s.setMaHD(rs.getInt("MaHD"));
                s.setNgayLapHoaDon(rs.getDate("NgayDat"));
                s.setMaSP(rs.getString("MaSP"));
                s.setMaKH(rs.getString("MaKH"));
                s.setTongTien(rs.getInt("TongTien"));
                listHD.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listHD;
    }
}
