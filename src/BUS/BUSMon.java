/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALDanhMuc;
import DAL.DALMon;
import DTO.Mon;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author DucThanh
 */
public class BUSMon {

    Random rd = new Random();

    public ArrayList<Mon> getAllMon()
    {
        String sql = "SELECT * FROM MON";
        return new DALMon().getMonList(sql);
    }
    public boolean themMon(Mon mon) {
        
        return new DALMon().addMon(mon);
    }

    public boolean setTrangThaiMon(String tenMon, boolean trangThai) {
        return new DALMon().updateStatus(tenMon, trangThai);
    }

    public boolean fixMon(Mon mon) {
        return new DALMon().updateMon(mon);
    }

    public ArrayList<Mon> queryMonByStatus(boolean status) {
        String sql = "select * from MON\n"
                + "where TRANGTHAI ='"+status+"' ";
        return new DALMon().getMonList(sql);
    }
}
