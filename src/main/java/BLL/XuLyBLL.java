/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.XuLy;
import DAL.XuLyDAL;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class XuLyBLL {
    private XuLyDAL XuLyDAL;

    public XuLyBLL() {
        XuLyDAL = new XuLyDAL();
    }
    
     public XuLy getXuLyThanhVienDangViPham(int MaTV) {
         return XuLyDAL.getXuLyThanhVienDangViPham(MaTV);
     }
}
