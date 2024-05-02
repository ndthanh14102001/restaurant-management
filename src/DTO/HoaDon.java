/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAL.DALHoaDon;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DucThanh
 */
public class HoaDon {
    String idHoaDon,idNVThanhToan;
    int idBan;
    Date ngay,gio;
    float tongtien;
    boolean trangThai;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("H:mm");
    public HoaDon(String idHoaDon, String idNVThanhToan, int idBan, String ngay, String gio, float tongtien) {
        this.idHoaDon = idHoaDon;
        this.idNVThanhToan = idNVThanhToan;
        this.idBan = idBan;
        try {
            this.ngay = formatDate.parse(ngay);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.gio = formatTime.parse(gio);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tongtien = tongtien;
        this.trangThai = false;
    }
    public HoaDon(String idHoaDon, String idNVThanhToan, int idBan, float tongtien) {
        this.idHoaDon = idHoaDon;
        this.idNVThanhToan = idNVThanhToan;
        this.idBan = idBan;
        this.ngay = new Date();
        this.gio = new Date();
        this.tongtien = tongtien;
        this.trangThai = false;
    }
    public HoaDon() {
        this.idHoaDon = null;
        this.idNVThanhToan = null;
        this.idBan = -1;
        this.ngay = new Date();
        this.gio = new Date();
        this.tongtien = 0;
        this.trangThai = false;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    public boolean checkIdHoaDon()
    {
        ArrayList<HoaDon> hoaDonList = new DALHoaDon().getHoaDonList();
        for (int i = 0; i < hoaDonList.size(); i++) {
            if(this.idHoaDon.equals(hoaDonList.get(i).getIdHoaDon()) )
                return true;
        }
        return false;
    }
    public void createDayAndHouse()
    {
        this.ngay = new Date();
        this.gio = new Date();
    }
    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdNVThanhToan() {
        return idNVThanhToan;
    }

    public void setIdNVThanhToan(String idNVThanhToan) {
        this.idNVThanhToan = idNVThanhToan;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getGio() {
        return gio;
    }

    public void setGio(Date gio) {
        this.gio = gio;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }
    public String getNgayThanhToan()
    {
        return formatDate.format(ngay);
    }
    public String getGioThanhToan()
    {
        return formatTime.format(gio);
    }

    @Override
    public String toString() {
        return "HoaDon{" + "idHoaDon=" + idHoaDon + ", idNVThanhToan=" + idNVThanhToan + ", idBan=" + idBan + ", ngay=" + ngay + ", gio=" + gio + ", tongtien=" + tongtien + ", trangThai=" + trangThai + '}';
    }
   
    
}
