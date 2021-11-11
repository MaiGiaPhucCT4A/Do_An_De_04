/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import model.DoanhThu;

/**
 *
 * @author LaptopDT
 */
public class DoanhThu_DAO {

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Do_An_De_04";
    String user = "sa";
    String password = "123456";
    Statement st;
    ResultSet rs;

    private Connection conn;

    public DoanhThu_DAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoanhThu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ThanhToan(DoanhThu dt) {
        String sql = "insert into DoanhThu(MaHD,NgayBan,TongTienThu,TienNhan,TienDu) values (?,GETDATE(),?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dt.getMaHD());
            ps.setInt(2, dt.getTongTienThu());
            ps.setInt(3, dt.getTienNhan());
            ps.setInt(4, dt.getTienDu());
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BanHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ThanhToan_DH(DoanhThu dt) {
        String sql = "insert into DoanhThu(MaHD,NgayBan,TongTienThu,TienNhan,TienDu) values (?,?,?,?,0)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dt.getMaHD());
            ps.setDate(2, new Date(dt.getNgayDat().getTime()));
            ps.setInt(3, dt.getTongTienThu());
            ps.setInt(4, dt.getTienNhan());
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BanHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DoanhThu> getListDoanhThu() {
        List<DoanhThu> listDT = new ArrayList<>();
        String sql = "select * from DoanhThu order by MaHD";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu dt = new DoanhThu();
                dt.setMaHD(rs.getString(1));
                dt.setNgayDat(rs.getDate(2));
                dt.setTongTienThu(rs.getInt(3));
                dt.setTienNhan(rs.getInt(4));
                dt.setTienDu(rs.getInt(5));
                listDT.add(dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDT;
    }

    public List<DoanhThu> getListSearch_DoanhThu(java.util.Date ngay1, java.util.Date ngay2, List<DoanhThu> listDT) {
        String sql = "select * from DoanhThu where NgayBan between '" + ngay1 + "' and '" + ngay2 + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu dt = new DoanhThu();
                dt.setMaHD(rs.getString(1));
                dt.setNgayDat(rs.getDate(2));
                dt.setTongTienThu(rs.getInt(3));
                dt.setTienNhan(rs.getInt(4));
                dt.setTienDu(rs.getInt(5));
                listDT.add(dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThu_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDT;
    }
}
