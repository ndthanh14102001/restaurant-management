/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Mon;
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
public class DALMon {
    Connection conn;
    public DALMon() {
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                        + "username=sa;password=123456");
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
    }
    public ArrayList<Mon> getMonList()
    {
        ArrayList<Mon> monList = new ArrayList<>();
        String sql = "SELECT * FROM MON\n"
                + "ORDER BY TENMON asc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Mon mon = new Mon();
                mon.setIdMon(rs.getString("ID"));
                mon.setTenMon(rs.getString("TENMON"));
                mon.setGia(rs.getFloat("GIA"));
                mon.setDanhMuc(rs.getInt("DANHMUC"));
                mon.setTrangThai(rs.getBoolean("TRANGTHAI"));
                mon.setLinkAnh(rs.getString("ANH"));
                
                monList.add(mon);
            }
        } catch (Exception e) {
        }
        return monList;
    }
    public boolean addMon(Mon mon)
    {
        String sql = "INSERT INTO MON(ID,TENMON,GIA,DANHMUC,ANH,TRANGTHAI) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mon.getIdMon());
            ps.setString(2, mon.getTenMon());
            ps.setFloat(3, mon.getGia());
            ps.setInt(4, mon.getDanhMuc());
            ps.setString(5,mon.getLinkAnh());
            ps.setBoolean(6,mon.isTrangThai());
            
            return ps.executeUpdate() >0;
        } catch (Exception e) {
        }
        return false;
    }
    public Mon getMonByTen(String tenMon)
    {
        Mon mon = new Mon();
        String sql = "SELECT * FROM MON WHERE TENMON = N" + "'" + tenMon +"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                mon.setIdMon(rs.getString("ID"));
                mon.setGia(rs.getFloat("GIA"));
                mon.setTenMon(rs.getString("TENMON"));
                mon.setDanhMuc(rs.getInt("DANHMUC"));
                mon.setTrangThai(rs.getBoolean("TRANGTHAI"));
                mon.setLinkAnh(rs.getString("ANH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return mon;
    }
    public Mon getMonById(String id)
    {
        Mon mon = new Mon();
        String sql = "SELECT * FROM MON WHERE ID = N" + "'" + id +"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                mon.setIdMon(rs.getString("ID"));
                mon.setGia(rs.getFloat("GIA"));
                mon.setTenMon(rs.getString("TENMON"));
                mon.setDanhMuc(rs.getInt("DANHMUC"));
                mon.setTrangThai(rs.getBoolean("TRANGTHAI"));
                mon.setLinkAnh(rs.getString("ANH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return mon;
    }
    public boolean updateStatus(String tenMon,boolean status )
    {
        String sql ="update MON\n" +
                    "set TRANGTHAI = ?\n" +
                    "where TENMON = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, tenMon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateMon(Mon mon)
    {
        String sql = "update MON\n" +
                    "set TENMON =?,GIA=?,TRANGTHAI=?,DANHMUC = ?,ANH=?\n" +
                    "where ID = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mon.getTenMon());
            ps.setFloat(2, mon.getGia());
            ps.setBoolean(3, mon.isTrangThai());
            ps.setInt(4, mon.getDanhMuc());
            ps.setString(5, mon.getLinkAnh());
            ps.setString(6, mon.getIdMon());
           
            
            return ps.executeUpdate() > 0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Mon> getMonList(String sql)
    {
        ArrayList<Mon> monList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Mon mon = new Mon();
                mon.setIdMon(rs.getString("ID"));
                mon.setTenMon(rs.getString("TENMON"));
                mon.setGia(rs.getFloat("GIA"));
                mon.setDanhMuc(rs.getInt("DANHMUC"));
                mon.setTrangThai(rs.getBoolean("TRANGTHAI"));
                mon.setLinkAnh(rs.getString("ANH"));
                
                monList.add(mon);
            }
        } catch (Exception e) {
        }
        return monList;
    } 
}
