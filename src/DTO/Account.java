/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author DucThanh
 */
public class Account {
    String id,userName,password,maNV;
    int permission;
    public Account(String id, String userName, String password,String maNV,int permission) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.permission = permission;
        this.maNV = maNV;
    }
    public Account() {
        this.id = null;
        this.userName = null;
        this.password = null;
        this.permission = -1;
        this.maNV = null;
    }
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public int getPermission() {
        return permission;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", maNV=" + maNV + ", permission=" + permission + '}';
    }
    
   
}
