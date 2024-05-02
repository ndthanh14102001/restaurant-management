/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.*;

/**
 *
 * @author DucThanh
 */
public class DALQuery {

    Connection conn;
    public DALQuery() {
        try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                        + "username=sa;password=123456");
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
    }
    public int queryBillofBan(int ban,String[] tenMon,int[] soLuong,float []donGia)
    {
        
        String sql = "SELECT MON.TENMON,CHITIETHOADON.SOLUONG,MON.GIA FROM HOADON,CHITIETHOADON,MON\n" +
                        "WHERE HOADON.BAN = " + "'" + ban + "'\n" +
                        "AND CHITIETHOADON.IDHOADON =HOADON.ID \n" +
                        "AND CHITIETHOADON.IDMON = MON.ID\n" +
                        "AND HOADON.TRANGTHAI = 0";
        int i=0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                tenMon[i]= rs.getString("TENMON");
                soLuong[i] = rs.getInt("SOLUONG");
                donGia[i] = rs.getFloat("GIA");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
