/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Quyen;
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
public class DALQuyen {
    Connection conn;
    public DALQuyen() {
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                        + "username=sa;password=123456");
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
    }
    public ArrayList<Quyen> getQuyenList(String sql)
    {
        ArrayList<Quyen> quyenList = new ArrayList<>();
//        String sql = "SELECT * FROM QUYEN";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Quyen quyen = new Quyen();
                quyen.setIdQuyen(rs.getString("IDQUYEN"));
                quyen.setTenQuyen(rs.getString("TENQUYEN"));
                quyen.setChiTietQuyen(rs.getString("CHITIETQUYEN"));
                
                quyenList.add(quyen);
            }
        } catch (Exception e) {
        }
        return quyenList;
    }
    public Quyen getQuyen(String sql)
    {
         Quyen quyen = new Quyen();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               
                quyen.setIdQuyen(rs.getString("IDQUYEN"));
                quyen.setTenQuyen(rs.getString("TENQUYEN"));
                quyen.setChiTietQuyen(rs.getString("CHITIETQUYEN"));
                
            }
        } catch (Exception e) {
        }
        return quyen;
    }
    public boolean updateQuyen(String sql)
    {
//        String sql = "INSERT INTO QUYEN(IDQUYEN,TENQUYEN,CHITIETQUYEN) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
           
           
            return ps.executeUpdate() >0;
        } catch (Exception e) {
        }
        return false;
    }
}
