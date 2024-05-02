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
public class Ban {
    int idBan;
    boolean trangThai;
    public Ban(int idBan, boolean trangThai) {
        this.idBan = idBan;
        this.trangThai = trangThai;
    }

    public Ban() {
        this.idBan = -1;
        this.trangThai = false;
    }
    
    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Ban{" + "idBan=" + idBan + ", trangThai=" + trangThai + '}';
    }
    
}
