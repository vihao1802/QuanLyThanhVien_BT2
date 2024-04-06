/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN
 */
public class ThietBiDAL {

    Session session;
    
    public ThietBiDAL()
    {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List loadThietBi()
    {
        List<ThietBi> list;
        session.beginTransaction();
        list = session.createQuery("FROM ThietBi",ThietBi.class).list();
        session.getTransaction().commit();
        return list;
    }

    public ThietBi getThietBi(int MaTB) {
        return session.get(ThietBi.class, MaTB);
    }
    public void add(ThietBi obj)
    {
        session.save(obj);
    }
    public void update(ThietBi obj)
    {
        session.update(obj);
    }
    public void delete(ThietBi obj)
    {
        session.delete(obj);
    }
    
    public int getSLThietBiTheoTen(String name) {
        String query = "SELECT COUNT(MaTB) "
                + "FROM ThietBi "
                + "WHERE TenTB='" + name + "'";

        session.beginTransaction();

        Query<Long> queryResult = session.createQuery(query, Long.class);
        Long count = queryResult.uniqueResult();

        session.getTransaction().commit();

        return count != null ? count.intValue() : 0;
    }
    
    public ArrayList<Integer> getTBTheoThoiGian(ArrayList<String> tbList, String time1, String time2) {
        ArrayList<Integer> soLuongList = new ArrayList<>();

        String query = "SELECT tb.TenTB, COUNT(tt.thietbi.MaTB) " +
               "FROM DAL.ThietBi tb, DAL.ThongTinSD tt " +
               "WHERE tb.MaTB = tt.thietbi.MaTB AND tt.TGMuon >= '" + time1 + "' AND tt.TGMuon <= '" + time2 + "' " +
               "GROUP BY tb.TenTB";


        session.beginTransaction();

        // Thực hiện truy vấn SQL
        Query<Object[]> queryResult = session.createQuery(query, Object[].class);
        List<Object[]> results = queryResult.list();

        // Duyệt qua kết quả và gán tên thiết bị và số lượng vào ArrayList
        for (Object[] result : results) {
            String tenTB = (String) result[0];
            Integer soLuong = ((Number) result[1]).intValue();
            tbList.add(tenTB);
            soLuongList.add(soLuong);
        }

        session.getTransaction().commit();

        return soLuongList;
    }

}
