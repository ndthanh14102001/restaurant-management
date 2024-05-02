/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class DALAccount {

    Connection conn;

    public DALAccount() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANAN;"
                    + "username=sa;password=123456");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Account> getAccountList(String sql) {
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getString("ID"));
                account.setUserName(rs.getString("TENTAIKHOAN"));
                account.setPassword(rs.getString("MATKHAU"));
                account.setMaNV(rs.getString("MANV"));
                account.setPermission(rs.getInt("MAQUYEN"));

                
                accountList.add(account);
            }
        } catch (Exception e) {
        }
        return accountList;
    }

    public boolean updateAccount(String sql) {
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
       
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Account query(String sql)
    {
        Account account = new Account();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                account.setId(rs.getString("ID"));
                account.setUserName(rs.getString("TENTAIKHOAN"));
                account.setPassword(rs.getString("MATKHAU"));
                account.setMaNV(rs.getString("MANV"));
                account.setPermission(rs.getInt("MAQUYEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    public Account queryAccount(String username) {
        Account account = new Account();
        String sql = "select * from TAIKHOAN\n"
                + "where TAIKHOAN.TENTAIKHOAN = ?" ;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account.setId(rs.getString("ID"));
                account.setUserName(rs.getString("TENTAIKHOAN"));
                account.setPassword(rs.getString("MATKHAU"));
                account.setMaNV(rs.getString("MANV"));
                account.setPermission(rs.getInt("MAQUYEN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

}
