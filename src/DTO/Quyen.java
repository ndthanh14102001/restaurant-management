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
public class Quyen {
    String idQuyen,tenQuyen,chiTietQuyen;

    public Quyen(String idQuyen, String tenQuyen,String chiTietQuyen) {
        this.idQuyen = idQuyen;
        this.tenQuyen = tenQuyen;
        this.chiTietQuyen = chiTietQuyen;
    }

    public Quyen() {
        this.idQuyen = null;
        this.tenQuyen = null;
        this.chiTietQuyen = null;
    }

    public String getChiTietQuyen() {
        return chiTietQuyen;
    }

    public void setChiTietQuyen(String chiTietQuyen) {
        this.chiTietQuyen = chiTietQuyen;
    }
    
    public String getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(String idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    @Override
    public String toString() {
        return "Quyen{" + "idQuyen=" + idQuyen + ", tenQuyen=" + tenQuyen + ", chiTietQuyen=" + chiTietQuyen + '}';
    }

    
    
}
