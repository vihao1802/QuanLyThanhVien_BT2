/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThietBiDAL;
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
}
