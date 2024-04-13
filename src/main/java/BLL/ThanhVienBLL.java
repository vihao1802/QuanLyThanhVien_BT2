/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package BLL;

import java.util.List;
import DAL.ThanhVienDAL;
import DAL.ThanhVien;
import java.util.ArrayList;

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
    
    public ThanhVien getThanhVien(int MaTV) {
        return thanhvienDAL.getThanhVien(MaTV);
    }
    
    public void delete(int MaTV) {
        thanhvienDAL.delete(MaTV);
    }
    
    public int getSLTongVao() {
        return thanhvienDAL.getSLTongVao();
    }

    public ArrayList<Integer> getSLTongVaoTheoNgay(ArrayList<String> gioList, String date) {
        return thanhvienDAL.getSLTongVaoTheoNgay(gioList,date);
    }
    
    public int getSLTongVaoTheoNgayNum(ArrayList<String> gioList, String date) {
        int total = 0;
        for (Integer tmp : thanhvienDAL.getSLTongVaoTheoNgay(gioList,date)) {
            total += tmp;
        }
        return total;
    }
    
    public int getSLTongVaoTheoKhoangNum(ArrayList<String> gioList, String date1, String date2) {
        int total = 0;
        for (Integer tmp : thanhvienDAL.getSLTongVaoTheoKhoang(gioList,date1, date2)) {
            total += tmp;
        }
        return total;
    }

    public ArrayList<Integer> getSLTongVaoTheoThang(ArrayList<String> gioList, String date, String year) {
        return thanhvienDAL.getSLTongVaoTheoThang(gioList,date,year);
    }
    
    public int getSLTongVaoTheoThangNum(ArrayList<String> gioList, String date, String year) {
        int total = 0;
        for (Integer tmp : thanhvienDAL.getSLTongVaoTheoThang(gioList,date, year)) {
            total += tmp;
        }
        return total;
    }
    
    public ArrayList<Integer> getSLTongVaoTheoNam(ArrayList<String> gioList, String date) {
        return thanhvienDAL.getSLTongVaoTheoNam(gioList,date);
    }
    
    public int getSLTongVaoTheoNamNum(ArrayList<String> gioList, String date) {
        int total = 0;
        for (Integer tmp : thanhvienDAL.getSLTongVaoTheoNam(gioList,date)) {
            total += tmp;
        }
        return total;
    }
}
