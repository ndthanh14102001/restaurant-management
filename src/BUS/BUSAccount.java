/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALAccount;
import DTO.Account;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class BUSAccount {

    public ArrayList<Account> getAllAccount() {
        String sql = "select * from TAIKHOAN";
        ArrayList<Account> listAccount = new DALAccount().getAccountList(sql);
        return listAccount;
    }
    public ArrayList<Account> queryListAccountByQuyen(int quyen) {
        String sql = "select * from TAIKHOAN where MAQUYEN = " + quyen;
        ArrayList<Account> listAccount = new DALAccount().getAccountList(sql);
        return listAccount;
    }
    public Account queryAccountByIdNhanVien(String id)
    {
        String sql = "select * from TAIKHOAN where MANV = '"+id+"'";
        Account account = new DALAccount().query(sql);
        
        return account;
    }
    public boolean addAccount(Account account) {
        String sql = "INSERT INTO  TAIKHOAN (ID,TENTAIKHOAN,MATKHAU,MANV,MAQUYEN) VALUES"
                + "('" + account.getId() + "','" + account.getUserName() + "','" + account.getPassword() + "','" + account.getMaNV() + "','" + account.getPermission() + "')";
        return new DALAccount().updateAccount(sql);
    }

    public boolean resetPassword(String userName) {
        String sql = "update TAIKHOAN\n"
                + "set MATKHAU = '"+1+"'\n"
                + "where TENTAIKHOAN = '"+userName+"'";
        return new DALAccount().updateAccount(sql);
    }
    public boolean updatePassword(String userName, String password)
    {
        String sql = "update TAIKHOAN\n"
                + "set MATKHAU = '"+password+"'\n"
                + "where TENTAIKHOAN = '"+userName+"'";
        return new DALAccount().updateAccount(sql);
    }
    public boolean updateQuyen(String username,int quyen)
    {
        String sql = "update TAIKHOAN set MAQUYEN = " + quyen + " Where TENTAIKHOAN='"+username+"'";
        return new DALAccount().updateAccount(sql);
    }
    public boolean deleteAccount(String id)
    {
        String sql = "delete TAIKHOAN\n"
                + "where ID='"+id+"'";
        return new DALAccount().updateAccount(sql);
    }
    public Account queryAccount(String username)
    {
        String sql = "select * from TAIKHOAN\n" +
                    "where TENTAIKHOAN = '"+username+"'";
        return new DALAccount().query(sql);
    }
    
}
