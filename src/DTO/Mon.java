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
public class Mon {
    String idMon,tenMon,linkAnh;
    float gia;
    int danhMuc;
    boolean trangThai;

    public Mon(String idMon, String tenMon, float gia,int danhMuc,boolean trangThai) {
        this.idMon = idMon;
        this.tenMon = tenMon;
        this.gia = gia;
        this.danhMuc = danhMuc;
        this.trangThai = trangThai;
        this.linkAnh = null;
    }

    public Mon() {
        this.idMon = null;
        this.tenMon = null;
        this.linkAnh = null;
        this.gia = 0;
        this.danhMuc = -1;
        trangThai = false;
    }

    public boolean isTrangThai() {
        return trangThai;
    }
    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    public int getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(int danhMuc) {
        this.danhMuc = danhMuc;
    }
    
    public String getIdMon() {
        return idMon;
    }

    public void setIdMon(String idMon) {
        this.idMon = idMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "Mon{" + "idMon=" + idMon + ", tenMon=" + tenMon + ", linkAnh=" + linkAnh + ", gia=" + gia + ", danhMuc=" + danhMuc + ", trangThai=" + trangThai + '}';
    }

    
    
}
