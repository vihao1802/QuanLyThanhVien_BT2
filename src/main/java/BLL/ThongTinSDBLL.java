package BLL;


import DAL.ThongTinSD;
import java.util.List;
import DAL.ThongTinSDDAL;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class ThongTinSDBLL {
    private ThongTinSDDAL ThongTinSDDAL;

    public ThongTinSDBLL() {
        ThongTinSDDAL = new ThongTinSDDAL();
    }
    
    public void add(ThongTinSD obj) {
        ThongTinSDDAL.add(obj);
    }
    public List getThietBiDangMuon() {
        List list;
        list = ThongTinSDDAL.getThietBiDangMuon();
        return list;
    }
    public List getThietBiDangMuonTrongNgay(Date date) {
        List list;
        list = ThongTinSDDAL.getThietBiDangMuonTrongNgay(date);
        return list;
    }
    public List getThietBiDangMuonTrongThang(int year, int month) {
        List list;
        list = ThongTinSDDAL.getThietBiDangMuonTrongThang(year, month);
        return list;
    }
    public List getThietBiDangMuonTrongNam(int year) {
        List list;
        list = ThongTinSDDAL.getThietBiDangMuonTrongNam(year);
        return list;
    }
    public List getThongTinSDTrongKhoangThoiGian(Date startDate, Date endDate) {
        List list;
        list = ThongTinSDDAL.getThongTinSDTrongKhoangThoiGian(startDate,endDate);
        return list;
    }
    
}
