/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

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
}
