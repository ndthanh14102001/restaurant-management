/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ChiTietHoaDon;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author DucThanh
 */
public class DALChiTietHoaDon {
    Connection conn;
    public DALChiTietHoaDon() {
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;username=sa;password=123456");
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
    }
    public ArrayList<ChiTietHoaDon> getChiTietHoaDonList()
    {
        ArrayList<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        String sql = "SELECT * FROM CHITIETHOADON";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ChiTietHoaDon hoadon = new ChiTietHoaDon();
                hoadon.setIdHoaDon(rs.getString("IDHOADON"));
                hoadon.setIdMon(rs.getString("IDMON"));
                hoadon.setSoluong(rs.getInt("SOLUONg"));
                hoadon.setTongGia(rs.getFloat("TONGGIA"));
                hoadon.setMoThem(rs.getBoolean("MONTHEM"));
                
                chiTietHoaDonList.add(hoadon);
            }
        } catch (Exception e) {
        }
        return chiTietHoaDonList;
    }
    public ChiTietHoaDon queryChiTietHoaDon(String sql)
    {
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                chiTietHoaDon.setIdHoaDon(rs.getString("IDHOADON"));
                chiTietHoaDon.setIdMon(rs.getString("IDMON"));
                chiTietHoaDon.setSoluong(rs.getInt("SOLUONg"));
                chiTietHoaDon.setTongGia(rs.getFloat("TONGGIA"));
                chiTietHoaDon.setMoThem(rs.getBoolean("MONTHEM"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietHoaDon;
    }
    public ArrayList<ChiTietHoaDon> getChiTietHoaDonList(String sql)
    {
        ArrayList<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ChiTietHoaDon hoadon = new ChiTietHoaDon();
                hoadon.setIdHoaDon(rs.getString("IDHOADON"));
                hoadon.setIdMon(rs.getString("IDMON"));
                hoadon.setSoluong(rs.getInt("SOLUONg"));
                hoadon.setTongGia(rs.getFloat("TONGGIA"));
                hoadon.setMoThem(rs.getBoolean("MONTHEM"));
                
                chiTietHoaDonList.add(hoadon);
            }
        } catch (Exception e) {
        }
        return chiTietHoaDonList;
    }
    public boolean updateChiTietHoaDon(String sql)
    {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            return ps.executeUpdate() >0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int countMonThem(String idHoaDon)
    {
        String sql = "select count(MONTHEM) AS 'So Luong' from CHITIETHOADON\n" +
                    "where MONTHEM = 'true'\n" +
                    "and IDHOADON = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,idHoaDon);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getInt("So Luong");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public ArrayList<ChiTietHoaDon> getChiTietHoaDonByIdHoaDon(String idHoaDon)
    {
        ArrayList<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        String sql = "SELECT * FROM CHITIETHOADON where IDHOADON = " + idHoaDon;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ChiTietHoaDon hoadon = new ChiTietHoaDon();
                hoadon.setIdHoaDon(rs.getString("IDHOADON"));
                hoadon.setIdMon(rs.getString("IDMON"));
                hoadon.setSoluong(rs.getInt("SOLUONg"));
                hoadon.setTongGia(rs.getFloat("TONGGIA"));
                hoadon.setMoThem(rs.getBoolean("MONTHEM"));
                
                chiTietHoaDonList.add(hoadon);
            }
        } catch (Exception e) {
        }
        return chiTietHoaDonList;
    }
    public boolean addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon)
    {
        String sql = "INSERT INTO CHITIETHOADON(IDMON,IDHOADON,SOLUONG,TONGGIA,MONTHEM) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, chiTietHoaDon.getIdMon());
            ps.setString(2, chiTietHoaDon.getIdHoaDon());
            ps.setInt(3, chiTietHoaDon.getSoluong());
            ps.setFloat(4, chiTietHoaDon.getTongGia());
            ps.setBoolean(5, chiTietHoaDon.isMoThem());
            
            return ps.executeUpdate() >0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkMon(String idMon,String idHoaDon,boolean monThem)
    {
        String sql = "SELECT COUNT(*) AS \"SO LUONG\"\n" +
                        "  FROM CHITIETHOADON\n" +
                        "  WHERE IDHOADON = ?\n" +
                        "AND IDMON= ?\n" +
                        "AND MONTHEM = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ps.setString(2, idMon);
            ps.setBoolean(3, monThem);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("SO LUONG")==1)
                    return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    public boolean themSoLuongMon(String idMon,String idHoaDon,int soLuong,float donGia,boolean monThem)
    {
        String sql = "update CHITIETHOADON\n" +
                    "set SOLUONG = (Select SOLUONG from CHITIETHOADON\n" +
                    "WHERE IDMON = ?\n" +
                    "AND IDHOADON = ?\n" +
                    "AND MONTHEM = ?) + ?,\n" +
                    "TONGGIA = ((Select SOLUONG from CHITIETHOADON\n" +
                    "WHERE IDMON = ?\n" +
                    "AND IDHOADON = ?\n" +
                    "AND MONTHEM = ?\n" +
                    ") +? )* ?\n" +
                    "WHERE IDMON = ?\n" +
                    "AND IDHOADON = ?\n"
                    + "AND MONTHEM = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idMon);
            ps.setString(2, idHoaDon);
            ps.setBoolean(3, monThem);
            ps.setInt(4, soLuong);
            ps.setString(5, idMon);
            ps.setString(6, idHoaDon);
            ps.setBoolean(7, monThem);
            ps.setInt(8, soLuong);
            ps.setFloat(9, donGia);
            ps.setString(10, idMon);
            ps.setString(11, idHoaDon);
            ps.setBoolean(12, monThem);
            return ps.executeUpdate()>0;
            
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public boolean upadateTongGia(String idMon,String idHoaDon)
    {
        String sql = "Update HOADON\n" +
                "set TONGTIEN = (\n" +
                "SELECT SUM(TONGGIA) AS \"TONGTIEN\"\n" +
                "FROM CHITIETHOADON\n" +
                "WHERE IDHOADON = ?)\n" +
                "where ID = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idHoaDon);
            ps.setString(2, idHoaDon);
            return ps.executeUpdate()>0;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
