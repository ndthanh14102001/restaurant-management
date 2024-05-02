/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALChiTietHoaDon;
import DTO.ChiTietHoaDon;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class BUSChiTietHoaDon {

    public ArrayList<ChiTietHoaDon> getAllChiTietHoaDonByIdHoaDon(String idHoaDon) {
        return new DALChiTietHoaDon().getChiTietHoaDonByIdHoaDon(idHoaDon);
    }

    public ChiTietHoaDon queryChiTietHoaDon(String idHoaDon, String idMon, boolean isThem) {
        String sql = "select * from CHITIETHOADON\n"
                + "where IDMON = '" + idMon + "' and IDHOADON ='" + idHoaDon + "' and MONTHEM = '" + isThem + "'";
        return new DALChiTietHoaDon().queryChiTietHoaDon(sql);
    }
    public ArrayList<ChiTietHoaDon> queryChiTietHoaDon(String idHoaDon) {
        String sql = "select * from CHITIETHOADON\n"
                + "where  IDHOADON ='" + idHoaDon + "'";
        return new DALChiTietHoaDon().getChiTietHoaDonList(sql);
    }
    public ArrayList<ChiTietHoaDon> queryChiTietHoaDon(String idHoaDon,boolean isMonthem) {
        String sql = "select * from CHITIETHOADON\n"
                + "where  IDHOADON ='" + idHoaDon + "' and MONTHEM = '" + isMonthem + "'";
        return new DALChiTietHoaDon().getChiTietHoaDonList(sql);
    }
    public boolean deleteChiTietHoaDon(String idHoaDon, String idMon, boolean isThem) {
        String sql = "delete CHITIETHOADON\n"
                + "where IDHOADON ='" + idHoaDon + "' \n"
                + "and IDMON = '" + idMon + "'\n"
                + "and MONTHEM = '" + isThem + "'";
        return new DALChiTietHoaDon().updateChiTietHoaDon(sql);
    }

    public boolean deleteChiTietHoaDon(String idHoaDon, String idMon) {
        String sql = "delete CHITIETHOADON\n"
                + "where IDHOADON ='" + idHoaDon + "' \n"
                + "and IDMON = '" + idMon + "'\n";
        return new DALChiTietHoaDon().updateChiTietHoaDon(sql);
    }

    public boolean updateSoLuong(String idHoaDon, String idMon, boolean isThem,int soluong,float dongia) {
        String sql = "update CHITIETHOADON\n"
                + "set SOLUONG= "+soluong+", TONGGIA = \n"
                + ""+soluong+" * "+dongia+"\n"
                + "where IDHOADON = '"+idHoaDon+"'\n"
                + "and IDMON ='"+idMon+"'\n"
                + "and MONTHEM ='"+isThem+"'";
        return new DALChiTietHoaDon().updateChiTietHoaDon(sql);
    }
}
