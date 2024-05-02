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
public class ChiTietHoaDon {
    String idMon,idHoaDon;
    int soluong;
    float tongGia;
    boolean monThem;

    public ChiTietHoaDon(String idMon, String idHoaDon, int soluong,float donGia,float tongGia,boolean monThem) {
        this.idMon = idMon;
        this.idHoaDon = idHoaDon;
        this.soluong = soluong;
        this.tongGia = tongGia;
        this.monThem = monThem;
    }

    public ChiTietHoaDon() {
        this.idMon = null;
        this.idHoaDon = null;
        this.soluong = 0;
        this.tongGia = 0;
        this.monThem = false;
    }

    public boolean isMonThem() {
        return monThem;
    }

    public void setMonThem(boolean monThem) {
        this.monThem = monThem;
    }

    
    public boolean isMoThem() {
        return monThem;
    }

    public void setMoThem(boolean monThem) {
        this.monThem = monThem;
    }
    
    public String getIdMon() {
        return idMon;
    }

    public void setIdMon(String idMon) {
        this.idMon = idMon;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getTongGia() {
        return tongGia;
    }

    public void setTongGia(float tongGia) {
        this.tongGia = tongGia;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "idMon=" + idMon + ", idHoaDon=" + idHoaDon + ", soluong=" + soluong + ", tongGia=" + tongGia +  ", monThem=" + monThem + '}';
    }
    
}
