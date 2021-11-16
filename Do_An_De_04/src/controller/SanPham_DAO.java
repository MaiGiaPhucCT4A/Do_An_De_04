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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
            System.out.println("Lỗi: " + ex);
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
        return false;
    }

    public void delectSanPham(String masp) {
        String sql = "delete from BanHang where MaSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masp);

            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSanPham(SanPham sp, String masp) {
        String sql = "update SanPham set TenSP=?, NhaSX=?, LoaiSP=?, "
                + "SoLuong=?, DonViTinh=?, GiaNhap=?, GiaBan=?, NSX=?, HSD=? "
                + "where MaSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(10, masp);
            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getNhaSanXuat());
            ps.setString(3, sp.getLoaiSP());
            ps.setInt(4, sp.getSl());
            ps.setString(5, sp.getDonViTinh());
            ps.setInt(6, sp.getGiaNhap());
            ps.setInt(7, sp.getGiaBan());
            ps.setDate(8, new Date(sp.getNgaySX().getTime()));
            ps.setDate(9, new Date(sp.getHSD().getTime()));
            int n = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
    }

    public void Update_SoLuongSP(SanPham sp, String tensp) {
        String sql = "update SanPham set SoLuong = ? where TenSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sp.getSl());
            ps.setString(2, tensp);
            int n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SanPham> LocSP_NhaSX(String nhaSX, List<SanPham> listSP) {

        String sql = "select * from SanPham where NhaSX = N'" + nhaSX + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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
        }
        return listSP;
    }

    public List<SanPham> LocTop10SP(int top, String sx, List<SanPham> listSP) {
        String sql = "select top " + top + " * from SanPham order by SoLuong " + sx;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham s = new SanPham();
                s.setMaSP(rs.getString("MaSP"));
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
        }
        return listSP;
    }

    public List<SanPham> LocTopSP_QuaHSD(List<SanPham> listSP) {
        String sql = "select * from SanPham where HSD < GETDATE()";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham s = new SanPham();
                s.setMaSP(rs.getString("MaSP"));
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
        }
        return listSP;
    }

    public List<SanPham> LocTopSP_NSXganNhat(List<SanPham> listSP) {
        String sql = "select TOP 5 * from SanPham order by ABS(DATEDIFF(mm,NSX,GETDATE()))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham s = new SanPham();
                s.setMaSP(rs.getString("MaSP"));
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
        }
        return listSP;
    }

    public List<SanPham> getListSanPham() { // đọc ở bảng
        List<SanPham> listSP = new ArrayList<>();
        String sql = "select * from SanPham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();// dòng đầu tiên trong bảng
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

    public void initComboBox_TenSanPham(JComboBox<String> combo_TenSP) {
        String sql = "select * from SanPham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            combo_TenSP.removeAllItems();
            while (rs.next()) {
                combo_TenSP.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(combo_TenSP, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void loadData_SanPham(JComboBox<String> ten_maSP, JComboBox<String> combo_maSP, JComboBox<String> combo_loaiSP,
            JTextField txtNSX, JTextField txtHSD, JTextField txtSL, JTextField txtGiaBan, JComboBox<String> combo_dv) {
        String sql = "select * from SanPham where TenSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten_maSP.getSelectedItem().toString());
            rs = ps.executeQuery();
            combo_maSP.removeAllItems();
            combo_loaiSP.removeAllItems();
            combo_dv.removeAllItems();
            while (rs.next()) {
                combo_maSP.addItem(rs.getString("MaSP").trim());
                combo_loaiSP.addItem(rs.getString("LoaiSP").trim());
                txtNSX.setText(rs.getString("NSX").trim());
                txtHSD.setText(rs.getString("HSD").trim());
                txtSL.setText(rs.getString("SoLuong").trim());
                txtGiaBan.setText(rs.getString("GiaBan").trim());
                combo_dv.addItem(rs.getString("DonViTinh").trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update_LoaiSP(SanPham sp) {
        String sql = "insert into LoaiSanPham values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getLoaiSP());

            int n = ps.executeUpdate();// update data
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
    }

    public void Update_NhaSX(SanPham sp) {
        String sql = "insert into NhaSanXuat values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getNhaSanXuat());

            int n = ps.executeUpdate();// update data
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException n) {
            System.out.println("Lỗi: " + n);
        }
    }

    public void initComboBox_LoaiSanPham(JComboBox<String> combo_LoaiSP) {
        String sql = "select distinct LoaiSP from LoaiSanPham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            combo_LoaiSP.removeAllItems();
            while (rs.next()) {
                combo_LoaiSP.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(combo_LoaiSP, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void initComboBox_NhaSX(JComboBox<String> combo_NhaSX) {
        String sql = "select distinct NhaSX from NhaSanXuat";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            combo_NhaSX.removeAllItems();
            while (rs.next()) {
                combo_NhaSX.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham_DAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(combo_NhaSX, ex.getMessage());
            ex.printStackTrace();
        }
    }
}
