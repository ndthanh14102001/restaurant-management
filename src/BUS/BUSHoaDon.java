/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALHoaDon;
import DTO.HoaDon;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author DucThanh
 */
public class BUSHoaDon {

    public ArrayList<HoaDon> getAllHoaDonByNgay(Date ngayBd, Date ngayKt) {
        ArrayList<HoaDon> listHoaDon = new DALHoaDon().queryListHoaDonByNgay(ngayBd, ngayKt);
        if (listHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không Tìm Được Hóa Đơn");
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getAllHoaDon(boolean status) {
        String sql = "select * from HOADON\n"
                + "where TRANGTHAI = '"+status+"'";
        return new DALHoaDon().getHoaDonList(sql);
    }
    public HoaDon queryHoaDon(String id)
    {
        String sql = "Select * from HOADON\n"
                + "where ID ='"+id+"'";
        return new DALHoaDon().getHoaDon(sql);
    }
    public HoaDon queryHoaDonByBan(int ban) {
        String sql = "SELECT * FROM HOADON,BAN"
                + " WHERE BAN.ID = " + ban
                + " AND HOADON.TRANGTHAI = 'false'"
                + " AND BAN.ID = HOADON.BAN";
        HoaDon hoaDon = new DALHoaDon().getHoaDon(sql);
        return hoaDon;
    }

    public void updateStatusHoaDon(String id, boolean status) {
        String sql = "Update HOADON\n"
                + "SET TRANGTHAI = '" + status + "'"
                + " \nWHERE ID = " + id;
        System.out.println(sql);
        if (new DALHoaDon().updateHoaDon(sql) == true) {
            JOptionPane.showMessageDialog(null, "In bill thanh cong");
        } else {
            JOptionPane.showMessageDialog(null, "In bill that bai");
        }
    }

    public boolean updateBanHoaDon(String idHoadon, int ban) {
        String sql = "Update HOADON "
                + "SET BAN = " + ban + "WHERE ID ='" + idHoadon + "'";
        return new DALHoaDon().updateHoaDon(sql);
    }

    public boolean updateTongTien(String idHoaDon) {
        String sql = "update HOADON\n"
                + "set TONGTIEN =(select SUM(TONGGIA)  FROM CHITIETHOADON\n"
                + "WHERE IDHOADON = '" + idHoaDon + "')\n"
                + "where ID = '" + idHoaDon + "'";
        return new DALHoaDon().updateHoaDon(sql);
    }
}
