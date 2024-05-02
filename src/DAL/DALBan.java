/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Ban;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author DucThanh
 */
public class DALBan {

    Connection conn;

    public DALBan() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                    + "username=sa;password=123456");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Ban> getBanList() {
        ArrayList<Ban> banList = new ArrayList<>();
        String sql = "SELECT * FROM BAN";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ban ban = new Ban();
                ban.setIdBan(rs.getInt("ID"));
                ban.setTrangThai(rs.getBoolean("TRANGTHAI"));

                banList.add(ban);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banList;
    }

    public boolean addBan(Ban ban) {
        String sql = "INSERT INTO BAN(ID,TRANGTHAI) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ban.getIdBan());
            ps.setBoolean(2, ban.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateTrangThai(int ban, boolean trangThai) {
        
        String sql = "UPDATE BAN \n"
                + " SET TRANGTHAI =" + "'" + trangThai + "'\n"
                + " WHERE ID = " + "'" + ban + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public Ban queryBan(int soBan) {
        String sql = "Select * from BAN\n" +
                        "Where ID = " + soBan;
        Ban ban = new Ban();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, soBan);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ban.setIdBan(rs.getInt("ID"));
                ban.setTrangThai(rs.getBoolean("TRANGTHAI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ban;
    }

}
