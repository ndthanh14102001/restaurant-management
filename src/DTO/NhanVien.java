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
public class NhanVien {
    String idNhanVien,stdNhanVien,tenNhanVien;
    boolean gioiTinhNhanVien,trangThai;

    public NhanVien(String idNhanVien, String stdNhanVien, String tenNhanVien, boolean gioiTinhNhanVien,boolean trangThai) {
        this.idNhanVien = idNhanVien;
        this.stdNhanVien = stdNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinhNhanVien = gioiTinhNhanVien;
        this.trangThai = trangThai;
    }

    public NhanVien() {
        this.idNhanVien = null;
        this.stdNhanVien = null;
        this.tenNhanVien = null;
        this.gioiTinhNhanVien = false;
        this.trangThai = false;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getStdNhanVien() {
        return stdNhanVien;
    }

    public void setStdNhanVien(String stdNhanVien) {
        this.stdNhanVien = stdNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public boolean isGioiTinhNhanVien() {
        return gioiTinhNhanVien;
    }

    public void setGioiTinhNhanVien(boolean gioiTinhNhanVien) {
        this.gioiTinhNhanVien = gioiTinhNhanVien;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "idNhanVien=" + idNhanVien + ", stdNhanVien=" + stdNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinhNhanVien=" + gioiTinhNhanVien + ", trangThai=" + trangThai + '}';
    }

    
}
