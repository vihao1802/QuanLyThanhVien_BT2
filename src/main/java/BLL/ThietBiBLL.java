/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThietBiDAL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThietBiBLL {
    private ThietBiDAL ThietBiDAL;

    public ThietBiBLL() {
        ThietBiDAL = new ThietBiDAL();
    }

    public List loadThietBi() {
        List list;
        list = ThietBiDAL.loadThietBi();
        return list;
    }
    
    public int getSLThietBiTheoTen(String name) {
        return ThietBiDAL.getSLThietBiTheoTen(name);
    }
    
    public ArrayList<Integer> getTBTheoThoiGian(ArrayList<String> tbList, String time1, String time2) {
        return ThietBiDAL.getTBTheoThoiGian(tbList, time1, time2);
    }
}
