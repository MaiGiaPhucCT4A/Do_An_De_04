/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;
import model.KhachHang;

/**
 *
 * @author LaptopDT
 */
public class KhachHang_Dao {

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Do_An_De_04";
    String user = "sa";
    String password = "123456";
    Statement st;
    ResultSet rs;
    private Connection conn;

    public KhachHang_Dao() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean AddKhachHang(KhachHang kh) {
        String sql = "insert into KhachHang values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getGT());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getPhoneNum());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi: " + ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
        return false;
    }

    public void UpdateKhachHang(KhachHang k, String MKH) {
        String sql = "update KhachHang set TenKH=?,GioiTinh=?,DiaChi=?,SDT=? where MaKH=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, k.getHoTen());
            ps.setString(2, k.getGT());
            ps.setString(3, k.getDiaChi());
            ps.setString(4, k.getPhoneNum());
            ps.setString(5, MKH);

            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
    }
//    public void UpdateKhachHang(KhachHang k, String MKH, String tenkh, String gt, String diachi, String sdt) {
//        String sql = "update KhachHang set TenKH=" + tenkh + ",GioiTinh=" + gt
//                + ",DiaChi=" + diachi + ",SDT=" + sdt + " where MaKH=" + MKH;
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            int n = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NullPointerException n) {
//            System.out.println("Lỗi: " + n);
//        }
//    }

    public List<KhachHang> getListKH() {
        List<KhachHang> listKH = new ArrayList<>();
        String sql = "select * from KhachHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang k = new KhachHang();
                k.setMaKH(rs.getString(1));
                k.setHoTen(rs.getString(2));
                k.setGT(rs.getString(3));
                k.setDiaChi(rs.getString(4));
                k.setPhoneNum(rs.getString(5));
                listKH.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return listKH;
    }

    public void initComboBox_KhachHang(JComboBox<String> combo_MaKH) {
        String sql = "select * from KhachHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            combo_MaKH.removeAllItems();
            while (rs.next()) {
                combo_MaKH.addItem(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(combo_MaKH, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void loadData_KhachHangCombo(JTextField name, JTextField sdt, JTextField diachi, JComboBox<String> jcombo_MaKH) {
        KhachHang kh = new KhachHang();
        String sql = "select * from KhachHang where MaKH = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jcombo_MaKH.getSelectedItem().toString());// thiết lập gt cho dấu ?(là tham số duy nhất trong câu lệnh sql)
            rs = ps.executeQuery();
            // ktra xem có dữ liệu trong rs không. nếu có thì hiển thị data
            if (rs.next()) {
                name.setText(rs.getString("TenKH").trim());
                sdt.setText(rs.getString("SDT").trim());
                diachi.setText(rs.getString("DiaChi").trim());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delectKH(JComboBox<String> cbx_maKH) {
        String sql = "delete from KhachHang where MaKH = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cbx_maKH.getSelectedItem().toString());
            cbx_maKH.removeItemAt(cbx_maKH.getSelectedIndex());
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
