/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN
 */
public class ThanhVienDAL {

    Session session;

    public ThanhVienDAL() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List loadThanhVien() {
        List list;
        session.beginTransaction();
        list = session.createQuery("FROM ThanhVien", ThanhVien.class).list();
        session.getTransaction().commit();
        return list;
    }

    public ThanhVien getThanhVien(int MaTV) {
        return session.get(ThanhVien.class, MaTV);
    }

    public void add(ThanhVien obj) {
        session.save(obj);
    }

    public void update(ThanhVien obj) {
        session.update(obj);
    }

    public void delete(ThanhVien obj) {
        session.delete(obj);
    }
    
    public ArrayList<Integer> getSLThanhVienTheoKhoa(ArrayList<String> khoaList) {
        ArrayList<Integer> soLuongList = new ArrayList<>();
        
        String query = "SELECT tv.Khoa, COUNT(DISTINCT tv.MaTV) " +
               "FROM DAL.ThanhVien tv, DAL.ThongTinSD tt " +
               "WHERE tv.MaTV = tt.thanhvien.MaTV " +
               "GROUP BY tv.Khoa";


        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên khoa và số lượng vào ArrayList
        for (Object[] result : results) {
            String khoa = (String) result[0];
            Integer soLuong = ((Number) result[1]).intValue();
            khoaList.add(khoa);
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }
    
    public ArrayList<Integer> getSLThanhVienTheoNganh(ArrayList<String> nganhList) {
        ArrayList<Integer> soLuongList = new ArrayList<>();
        
        String query = "SELECT tv.Nganh, COUNT(DISTINCT tv.MaTV) " +
               "FROM DAL.ThanhVien tv, DAL.ThongTinSD tt " +
               "WHERE tv.MaTV = tt.thanhvien.MaTV " +
               "GROUP BY tv.Nganh";


        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên ngành và số lượng vào ArrayList
        for (Object[] result : results) {
            String khoa = (String) result[0];
            Integer soLuong = ((Number) result[1]).intValue();
            nganhList.add(khoa);
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }
}
