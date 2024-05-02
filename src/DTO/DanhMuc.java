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
public class DanhMuc {
    int id;
    String ten;

    public DanhMuc(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public DanhMuc() {
        this.id = -1;
        this.ten = null;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "DanhMuc{" + "id=" + id + ", ten=" + ten + '}';
    }
    
}
