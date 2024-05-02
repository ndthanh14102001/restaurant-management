/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.*;
import DTO.DanhMuc;
import DTO.Mon;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class DALDanhMuc {

    Connection conn;

    public DALDanhMuc() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;username=sa;password=123456");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addDanhMuc(DanhMuc danhMuc) {
        String sql = "INSERT INTO DANHMUC(ID,TEN) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, danhMuc.getId());
            ps.setString(2, danhMuc.getTen());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<DanhMuc> getDanhMucList() {
        ArrayList<DanhMuc> danhMucList = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setId(rs.getInt("ID"));
                danhMuc.setTen(rs.getString("TEN"));

                danhMucList.add(danhMuc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhMucList;
    }

    public ArrayList<Mon> queryDanhMuc(String name) {
        ArrayList<Mon> monList = new ArrayList<>();
        String sql = "SELECT MON.ID,MON.TENMON,MON.GIA,MON.DANHMUC,MON.ANH  FROM MON,DANHMUC\n"
                + "WHERE DANHMUC.TEN = N'" + name + "' "
                + "AND MON.DANHMUC = DANHMUC.ID ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mon mon = new Mon();
                mon.setIdMon(rs.getString("ID"));
                mon.setGia(rs.getFloat("GIA"));
                mon.setTenMon(rs.getString("TENMON"));
                mon.setDanhMuc(rs.getInt("DANHMUC"));
                mon.setLinkAnh(rs.getString("ANH"));

                monList.add(mon);
            }
        } catch (Exception e) {
        }
        return monList;
    }

    public ArrayList<Mon> queryDanhMuc(String name, boolean status) {
        ArrayList<Mon> monList = new ArrayList<>();
        String sql = "SELECT MON.ID,MON.TENMON,MON.GIA,MON.DANHMUC,MON.ANH  FROM MON,DANHMUC\n"
                + "WHERE DANHMUC.TEN = N'" + name + "' "
                + "AND MON.DANHMUC = DANHMUC.ID"
                + " and MON.TRANGTHAI ='" + status + "' ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mon mon = new Mon();
                mon.setIdMon(rs.getString("ID"));
                mon.setGia(rs.getFloat("GIA"));
                mon.setTenMon(rs.getString("TENMON"));
                mon.setDanhMuc(rs.getInt("DANHMUC"));
                mon.setLinkAnh(rs.getString("ANH"));

                monList.add(mon);
            }
        } catch (Exception e) {
        }
        return monList;
    }

    public DanhMuc queryDanhMucByName(String name) {
        String sql = "SELECT * FROM DANHMUC\n"
                + "WHERE TEN = ?";
        DanhMuc danhMuc = new DanhMuc();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                danhMuc.setId(rs.getInt("ID"));
                danhMuc.setTen(rs.getString("TEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhMuc;
    }

    public DanhMuc queryDanhMucById(int id) {
        String sql = "SELECT * FROM DANHMUC\n"
                + "WHERE ID = ?";
        DanhMuc danhMuc = new DanhMuc();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                danhMuc.setId(rs.getInt("ID"));
                danhMuc.setTen(rs.getString("TEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhMuc;
    }


}
