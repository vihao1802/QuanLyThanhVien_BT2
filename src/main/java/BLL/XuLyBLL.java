/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThanhVien;
import DAL.XuLy;
import DAL.XuLyDAL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class XuLyBLL {
    private XuLyDAL XuLyDAL;
    private List<XuLy> listXuLy;
    public XuLyBLL() {
        XuLyDAL = new XuLyDAL();
        listXuLy = new ArrayList<>();
        listXuLy = XuLyDAL.getAllXuly();
    }
    
    public XuLy getXuLyThanhVienDangViPham(int MaTV) {
         return XuLyDAL.getXuLyThanhVienDangViPham(MaTV);
     }
     
    public XuLy getXuLy(int XuLyID) {
        return XuLyDAL.getXuLy(XuLyID);
    }
    public List<XuLy> getAllXuly() {
        return listXuLy;
    }
    
    public void addXuLy(XuLy obj) {
        XuLyDAL.add(obj);
    }
    
    public void updateXuLy(XuLy obj) {
        XuLyDAL.update(obj);
    }
    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public boolean validateInput(String maTVStr, String hinhThucXL) {
        if (isNullOrEmpty(maTVStr)) {
            return false;
        }
        if (isNullOrEmpty(hinhThucXL)) {
            return false;
        }
        // Thêm các kiểm tra khác ở đây nếu cần
        return true;
    }
    public boolean validatePositiveNumber(String input) {
        return input.matches("\\d+") && !input.equals("0");
    }
    public boolean validateDateFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        try {
            Date parsedDate = dateFormat.parse(dateString);
            return dateString.equals(dateFormat.format(parsedDate));
        } catch (ParseException e) {
            return false;
        }
    }
}
