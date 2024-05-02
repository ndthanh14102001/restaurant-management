/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALNhanVien;
import DTO.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class BUSNhanVien {

    public ArrayList<NhanVien> getAllNhanVien() {
        return new DALNhanVien().getNhanVienList();
    }

    public NhanVien queryNhanVienByTen(String tenNhanVien) {
        String sql = "select * from NHANVIEN where TEN = N'" + tenNhanVien + "'";
        return new DALNhanVien().queryNhanVien(sql);
    }

    public ArrayList<NhanVien> getAllNhanVienByQuyen(int quyen) {
        String sql = "select * from NHANVIEN where ";
        return new DALNhanVien().getListNhanVien(sql);
    }

    public boolean updateNhanVien(NhanVien nhanVien) {
        String sql = "update NHANVIEN\n"
                + "set TEN = N'"+nhanVien.getTenNhanVien()+"',SDT='"+nhanVien.getStdNhanVien()+"',GIOITINH = '"+nhanVien.isGioiTinhNhanVien()+"',TRANGTHAI='"+nhanVien.isTrangThai()+"'\n"
                + "where ID ='"+nhanVien.getIdNhanVien()+"'";
        return new DALNhanVien().updateNhanVien(sql);
    }

}
