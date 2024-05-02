/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALQuyen;
import DTO.Quyen;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class BUSQuyen {

    public Object queryQuyenById;

    public ArrayList<Quyen> getAllQuyen() {
        String sql = "SELECT * FROM QUYEN";

        ArrayList<Quyen> listQuyen = new DALQuyen().getQuyenList(sql);
        return listQuyen;
    }

    public Quyen queryQuyenById(int id) {
        String sql = "select * from QUYEN\n"
                + "where IDQUYEN = '"+id+"'";
        Quyen quyen = new DALQuyen().getQuyen(sql);
        return quyen;
    }
    public Quyen queryQuyenByTen(String ten) {
        String sql = "select * from QUYEN\n"
                + "where TENQUYEN = N'"+ten+"'";
        Quyen quyen = new DALQuyen().getQuyen(sql);
        return quyen;
    }
}
