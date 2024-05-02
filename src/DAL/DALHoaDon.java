/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.HoaDon;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.text.ParseException;

/**
 *
 * @author DucThanh
 */
public class DALHoaDon {

    Connection conn;

    public DALHoaDon() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                    + "username=sa;password=123456");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<HoaDon> getHoaDonList() {
        ArrayList<HoaDon> hoadonList = new ArrayList<>();
        String sql = "SELECT * FROM HOADON";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setIdHoaDon(rs.getString("ID"));
                hoadon.setIdNVThanhToan(rs.getString("NVTT"));
                hoadon.setNgay(new Date(rs.getDate("NGAY").getTime()));
                hoadon.setGio(new SimpleDateFormat("H:mm").parse(rs.getString("GIO")));
                hoadon.setIdBan(rs.getInt("BAN"));
                hoadon.setTongtien(rs.getFloat("TONGTIEN"));
                hoadon.setTrangThai(rs.getBoolean("TRANGTHAI"));

                hoadonList.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoadonList;
    }
    public ArrayList<HoaDon> getHoaDonList(String sql) {
        ArrayList<HoaDon> hoadonList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setIdHoaDon(rs.getString("ID"));
                hoadon.setIdNVThanhToan(rs.getString("NVTT"));
                hoadon.setNgay(new Date(rs.getDate("NGAY").getTime()));
                hoadon.setGio(new SimpleDateFormat("H:mm").parse(rs.getString("GIO")));
                hoadon.setIdBan(rs.getInt("BAN"));
                hoadon.setTongtien(rs.getFloat("TONGTIEN"));
                hoadon.setTrangThai(rs.getBoolean("TRANGTHAI"));

                hoadonList.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoadonList;
    }
    public HoaDon getHoaDon(String sql)
    {
        HoaDon hoaDon = new HoaDon();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hoaDon.setIdHoaDon(rs.getString("ID"));
                hoaDon.setIdNVThanhToan(rs.getString("NVTT"));
                hoaDon.setNgay(new Date(rs.getDate("NGAY").getTime()));
                hoaDon.setGio(new SimpleDateFormat("H:mm").parse(rs.getString("GIO")));
                hoaDon.setIdBan(rs.getInt("BAN"));
                hoaDon.setTongtien(rs.getFloat("TONGTIEN"));
                hoaDon.setTrangThai(rs.getBoolean("TRANGTHAI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }
    public boolean updateHoaDon(String sql)
    {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO HOADON(ID,NVTT,BAN,NGAY,TONGTIEN,GIO,TRANGTHAI) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoaDon.getIdHoaDon());
            ps.setString(2, hoaDon.getIdNVThanhToan());
            ps.setInt(3, hoaDon.getIdBan());
            ps.setDate(4, new java.sql.Date(hoaDon.getNgay().getTime()));
            ps.setFloat(5, hoaDon.getTongtien());
            ps.setString(6, hoaDon.getGioThanhToan());
            ps.setBoolean(7, hoaDon.isTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean setStatusHoaDon(String id, boolean status) {
        java.util.Date datte = new java.util.Date();
        
        String sql = "Update HOADON\n"
                + "SET TRANGTHAI = ?,GIO='"+new SimpleDateFormat("H:mm").format(datte)+"'\n"
                + "WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, id);
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public HoaDon queryHoaDonByBan(int ban) {
        String sql = "SELECT * FROM HOADON,BAN\n"
                + "WHERE HOADON.BAN = BAN.ID\n"
                + "AND BAN.ID = ?";
        HoaDon hoadon = new HoaDon();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ban);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hoadon.setIdHoaDon(rs.getString("ID"));
                hoadon.setIdNVThanhToan(rs.getString("NVTT"));
                hoadon.setNgay(new Date(rs.getDate("NGAY").getTime()));
                hoadon.setGio(new SimpleDateFormat("H:mm").parse(rs.getString("GIO")));
                hoadon.setIdBan(rs.getInt("BAN"));
                hoadon.setTongtien(rs.getFloat("TONGTIEN"));
                hoadon.setTrangThai(rs.getBoolean("TRANGTHAI"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return hoadon;
    }

    public ArrayList<HoaDon> queryListHoaDonByNgay(java.util.Date ngaybd, java.util.Date ngaykt) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String sql = "select * from HOADON \n"
                + "where NGAY between ? and ?\n"
                + "and TRANGTHAI = 'true'\n"
                + "ORDER BY NGAY desc,GIO desc";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(ngaybd.getTime()));
            ps.setDate(2, new java.sql.Date(ngaykt.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdHoaDon(rs.getString("ID"));
                hoaDon.setIdBan(rs.getInt("BAN"));
                hoaDon.setIdNVThanhToan(rs.getString("NVTT"));
                hoaDon.setTongtien(rs.getFloat("TONGTIEN"));
                hoaDon.setTrangThai(rs.getBoolean("TRANGTHAI"));
                hoaDon.setNgay(rs.getDate("NGAY"));
                hoaDon.setGio(new SimpleDateFormat("H:mm").parse(rs.getString("GIO")));
                listHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDon;
    }

    
}
