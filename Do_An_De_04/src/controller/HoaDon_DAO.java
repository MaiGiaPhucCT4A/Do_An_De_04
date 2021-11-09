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
import javax.swing.JOptionPane;
import model.DatHang;
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

    public boolean AddHoaDon(DatHang HD) {
        String sql = "insert into DonDatHang(MaHD,NgayDat,MaSP,MaKH,SoLuongMua,TongTien) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, HD.getMaHD());
            ps.setDate(2, new Date(HD.getNgayLapHoaDon().getTime()));
            ps.setString(3, HD.getMaSP());
            ps.setString(4, HD.getMaKH());
            ps.setInt(5, HD.getSoLuongMua());
            ps.setInt(6, HD.getTongTien());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void delectSanPham(String masp) {
        String sql = "delete from DonDatHang where MaSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masp);

            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delectHD(JComboBox<String> cbx_mahd) {
        String sql = "delete from DonDatHang where MaHD = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cbx_mahd.getSelectedItem().toString());
            cbx_mahd.removeItemAt(cbx_mahd.getSelectedIndex());
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initComboBox_MaDH(JComboBox<String> combo_MaDH) {
        String sql = "select distinct maHD from DonDatHang order by MaHD";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            combo_MaDH.removeAllItems();
            while (rs.next()) {
                combo_MaDH.addItem(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_Dao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(combo_MaDH, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<DatHang> getListHoaDon() { // đọc ở bảng
        List<DatHang> listHD = new ArrayList<>();
        String sql = "select * from DonDatHang order by MaHD";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                DatHang s = new DatHang();
                s.setMaHD(rs.getString("MaHD"));
                s.setNgayLapHoaDon(rs.getDate("NgayDat"));
                s.setMaSP(rs.getString("MaSP"));
                s.setMaKH(rs.getString("MaKH"));
                s.setSoLuongMua(rs.getInt("SoLuongMua"));
                s.setTongTien(rs.getInt("TongTien"));
                listHD.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listHD;
    }

    public void LuuMaHoaDon(DatHang HD) {
        String sql = "insert into MaDonHang(MaHD) values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, HD.getMaHD());
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DatHang> MaDonDatHang() {
        List<DatHang> listHD = new ArrayList<>();
        String sql = "select * from MaDonHang order by MaHD";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                DatHang s = new DatHang();
                s.setMaHD(rs.getString("MaHD"));
                listHD.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listHD;
    }

    public List<DatHang> getList_DS_HoaDon() { // đọc ở bảng
        List<DatHang> listHD = new ArrayList<>();
        String sql = "select * from DonDatHang a,KhachHang b,SanPham c "
                + "where a.MaKH=b.MaKH and a.MaSP=c.MaSP order by MaHD";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                DatHang s = new DatHang();
                s.setMaHD(rs.getString("MaHD"));
                s.setNgayLapHoaDon(rs.getDate("NgayDat"));
                s.setMaSP(rs.getString("MaSP"));
                s.setMaKH(rs.getString("MaKH"));
                s.setSoLuongMua(rs.getInt("SoLuongMua"));
                s.setTongTien(rs.getInt("TongTien"));
                s.setTenSP(rs.getString("TenSP"));
                s.setTenKH(rs.getString("TenKH"));
                listHD.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listHD;
    }

    public List<DatHang> ListSearch_DS_HoaDon(String mahd, List<DatHang> listHD) { // đọc ở bảng
        String sql = "select * from DonDatHang where maHD = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
                DatHang s = new DatHang();
                s.setMaHD(rs.getString("MaHD"));
                s.setNgayLapHoaDon(rs.getDate("NgayDat"));
                s.setMaSP(rs.getString("MaSP"));
                s.setMaKH(rs.getString("MaKH"));
                s.setSoLuongMua(rs.getInt("SoLuongMua"));
                s.setTongTien(rs.getInt("TongTien"));
                listHD.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return listHD;
    }
//    public List<DatHang> ListSearch_DS_HoaDon(String mahd, List<DatHang> listHD) { // đọc ở bảng
//        String sql = "select MaHD,NgayDat,a.MaSP,a.MaKH,SoLuongMua,TongTien,TenSP,TenKH from DonDatHang a,KhachHang b,SanPham c "
//                + "where a.MaKH=b.MaKH and a.MaSP=c.MaSP and maHD = ? order by MaHD";
////        String sql = "select * from DonDatHang a,DoanhThu b where a.MaHD=b.MaHD order by MaHD";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, mahd);
//            rs = ps.executeQuery();// dòng đầu tiên trong bảng
//            while (rs.next()) { // bộ dữ liệu( dòng tiếp theo)
//                DatHang s = new DatHang();
//                s.setMaHD(rs.getString("MaHD"));
//                s.setNgayLapHoaDon(rs.getDate("NgayDat"));
//                s.setMaSP(rs.getString("MaSP"));
//                s.setMaKH(rs.getString("MaKH"));
//                s.setSoLuongMua(rs.getInt("SoLuongMua"));
//                s.setTongTien(rs.getInt("TongTien"));
//                s.setTenSP(rs.getString("TenSP"));
//                s.setTenKH(rs.getString("TenKH"));
//
//                listHD.add(s);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//        return listHD;
//    }
}
