/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.qlthanhvien_bt2;
import DAL.ThanhVien;
import DAL.ThanhVienDAL;
import GUI.MainForm;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class QLThanhVien_BT2 {

    public static void main(String[] args) {
        ThanhVienDAL tvDAL = new ThanhVienDAL();
        List<ThanhVien> tvlist = tvDAL.loadThanhVien();
        for (ThanhVien tv : tvlist) {
            System.out.print(tv.getListXuLy()+ "\n");
        }
        new MainForm();
    }
}
