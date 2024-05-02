/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.DALDanhMuc;
import DTO.DanhMuc;
import java.util.ArrayList;

/**
 *
 * @author DucThanh
 */
public class BUSDanhMuc {
    public ArrayList<DanhMuc> getListDanhMuc()
    {
        return new DALDanhMuc().getDanhMucList();
    }
}
