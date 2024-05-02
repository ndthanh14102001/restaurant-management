/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class DALNhanVien {

    Connection conn;

    public DALNhanVien() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                    + "username=sa;password=123456");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<NhanVien> getNhanVienList() {
        ArrayList<NhanVien> nhanVienList = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN\n"
                    + "ORDER BY TEN asc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setIdNhanVien(rs.getString("ID"));
                nhanVien.setTenNhanVien(rs.getString("TEN"));
                nhanVien.setStdNhanVien(rs.getString("SDT"));
                nhanVien.setGioiTinhNhanVien(rs.getBoolean("GIOITINH"));
                nhanVien.setTrangThai(rs.getBoolean("TRANGTHAI"));

                nhanVienList.add(nhanVien);
            }
        } catch (Exception e) {
        }
        return nhanVienList;
    }

    public ArrayList<NhanVien> getListNhanVien(String sql) {
        ArrayList<NhanVien> nhanVienList = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setIdNhanVien(rs.getString("ID"));
                nhanVien.setTenNhanVien(rs.getString("TEN"));
                nhanVien.setStdNhanVien(rs.getString("SDT"));
                nhanVien.setGioiTinhNhanVien(rs.getBoolean("GIOITINH"));
                nhanVien.setTrangThai(rs.getBoolean("TRANGTHAI"));

                nhanVienList.add(nhanVien);
            }
        } catch (Exception e) {
        }
        return nhanVienList;
    }

    public NhanVien queryNhanVien(String sql) {
        NhanVien nhanvien = new NhanVien();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nhanvien = new NhanVien(rs.getString("ID"), rs.getString("SDT"), rs.getString("TEN"), rs.getBoolean("GIOITINH"), rs.getBoolean("TRANGTHAI"));
            }
        } catch (Exception e) {
        }
        return nhanvien;
    }

    public boolean updateNhanVien(String sql) {

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addNhanVien(NhanVien nhanVien) {
        String sql = "INSERT INTO NHANVIEN(ID,TEN,SDT,GIOITINH,TRANGTHAI) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nhanVien.getIdNhanVien());
            ps.setString(2, nhanVien.getTenNhanVien());
            ps.setString(3, nhanVien.getStdNhanVien());
            ps.setBoolean(4, nhanVien.isGioiTinhNhanVien());
            ps.setBoolean(5, nhanVien.isTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public NhanVien queryNhanVienById(String id) {
        NhanVien nhanvien = new NhanVien();
        String sql = "select * from NHANVIEN\n"
                + "where NHANVIEN.ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nhanvien = new NhanVien(rs.getString("ID"), rs.getString("SDT"), rs.getString("TEN"), rs.getBoolean("GIOITINH"), rs.getBoolean("TRANGTHAI"));
            }
        } catch (Exception e) {
        }
        return nhanvien;
    }

}
