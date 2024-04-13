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

    public void delete(int MaTV) {
        ThanhVien tv = session.get(ThanhVien.class, MaTV);
        session.delete(tv);
        session.beginTransaction();
        session.getTransaction().commit();
    }

    public ArrayList<Integer> getSLThanhVienTheoKhoa(ArrayList<String> khoaList) {
        ArrayList<Integer> soLuongList = new ArrayList<>();

        String query = "SELECT tv.Khoa, COUNT(DISTINCT tv.MaTV) "
                + "FROM DAL.ThanhVien tv, DAL.ThongTinSD tt "
                + "WHERE tv.MaTV = tt.thanhvien.MaTV "
                + "GROUP BY tv.Khoa";

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

        String query = "SELECT tv.Nganh, COUNT(DISTINCT tv.MaTV) "
                + "FROM DAL.ThanhVien tv, DAL.ThongTinSD tt "
                + "WHERE tv.MaTV = tt.thanhvien.MaTV "
                + "GROUP BY tv.Nganh";

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

    public int getSLTongVao() {
        String query = "SELECT COUNT(TGVao) "
                + "FROM ThongTinSD "
                + "WHERE TGVao IS NOT NULL";

        session.beginTransaction();

        Query<Long> queryResult = session.createQuery(query, Long.class);
        Long count = queryResult.uniqueResult();

        session.getTransaction().commit();

        return count != null ? count.intValue() : 0;
    }

    public ArrayList<Integer> getSLTongVaoTheoNgay(ArrayList<String> gioList, String date) {
        ArrayList<Integer> soLuongList = new ArrayList<>();
        String query = "SELECT\n"
                + "    DATE(TGVao) AS Ngay,\n"
                + "    CONCAT(HOUR(TGVao), 'h-', HOUR(TGVao) + 1, 'h') AS HourRange,\n"
                + "    COUNT(*) AS Count\n"
                + "FROM\n"
                + "    ThongTinSD\n"
                + "WHERE\n"
                + "    TGVao IS NOT NULL AND\n"
                + "    DATE(TGVao) = '"+date+"'\n"
                + "GROUP BY\n"
                + "    DATE(TGVao), HOUR(TGVao)";

        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên ngành và số lượng vào ArrayList
        for (Object[] result : results) {
            String khoa = (String) result[1];
            Integer soLuong = ((Number) result[2]).intValue();
            gioList.add(khoa);
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }
   
    public ArrayList<Integer> getSLTongVaoTheoKhoang(ArrayList<String> gioList, String startDate, String endDate) {
        ArrayList<Integer> soLuongList = new ArrayList<>();
        String query = "SELECT\n"
                + "    DATE(TGVao) AS Ngay,\n"
                + "    CONCAT(HOUR(TGVao), 'h-', HOUR(TGVao) + 1, 'h') AS HourRange,\n"
                + "    COUNT(*) AS Count\n"
                + "FROM\n"
                + "    ThongTinSD\n"
                + "WHERE\n"
                + "    TGVao IS NOT NULL AND\n"
                + "    DATE(TGVao) BETWEEN '" + startDate + "' AND '" + endDate + "'\n"
                + "GROUP BY\n"
                + "HOUR(TGVao)";

        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên ngành và số lượng vào ArrayList
        for (Object[] result : results) {
            String khoa = (String) result[1];
            Integer soLuong = ((Number) result[2]).intValue();
            gioList.add(khoa);
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }


    public ArrayList<Integer> getSLTongVaoTheoThang(ArrayList<String> gioList, String date, String year) {
        ArrayList<Integer> soLuongList = new ArrayList<>();
        String query = "SELECT \n"
                + "    DAY(TGVao) AS Ngay,\n"
                + "    COUNT(TGVao) AS Count\n"
                + "FROM \n"
                + "    ThongTinSD\n"
                + "WHERE \n"
                + "    MONTH(TGVao) = " + date + "\n"
                + "AND YEAR(TGVao)= " + year + " GROUP BY \n"
                + "    DAY(TGVao)";

        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên ngày và số lượng vào ArrayList
        for (Object[] result : results) {
            Integer ngay = (Integer) result[0]; // Lấy giá trị của cột Ngay (vị trí 0)
            Integer soLuong = ((Number) result[1]).intValue(); // Lấy giá trị của cột Count (vị trí 1)
            gioList.add(ngay.toString()); // Chuyển đổi ngày thành chuỗi và thêm vào danh sách
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }
    
    public ArrayList<Integer> getSLTongVaoTheoNam(ArrayList<String> gioList, String date) {
        ArrayList<Integer> soLuongList = new ArrayList<>();
        String query = "SELECT MONTH(TGVao) AS Thang, COUNT(TGVao) "
                    + "FROM ThongTinSD "
                    + "WHERE YEAR(TGVao) = " + date + " AND TGVao IS NOT NULL "
                    + "GROUP BY MONTH(TGVao)";

        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên ngày và số lượng vào ArrayList
        for (Object[] result : results) {
            Integer ngay = (Integer) result[0]; // Lấy giá trị của cột Ngay (vị trí 0)
            Integer soLuong = ((Number) result[1]).intValue(); // Lấy giá trị của cột Count (vị trí 1)
            gioList.add(ngay.toString()); // Chuyển đổi ngày thành chuỗi và thêm vào danh sách
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }

}
