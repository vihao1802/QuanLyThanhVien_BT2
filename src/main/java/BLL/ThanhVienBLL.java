/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import java.util.List;
import DAL.ThanhVienDAL;
import DAL.ThanhVien;

/**
 *
 * @author ADMIN
 */
public class ThanhVienBLL {
    private ThanhVienDAL thanhvienDAL;

    public ThanhVienBLL() {
        thanhvienDAL = new ThanhVienDAL();
    }

    public List loadThanhVien() {
        List list;
        list = thanhvienDAL.loadThanhVien();
        return list;
    }
}
