/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author ADMIN
 */
public class ThongTinSDDAL {
    Session session;
    
    public ThongTinSDDAL()
    {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public ThongTinSD getThongTinSD(int ThongTinSDID)
    {
        ThongTinSD obj;
        session.beginTransaction();
        obj = session.get(ThongTinSD.class, ThongTinSDID);
        session.getTransaction().commit();
        return obj;
        
    }
    public List getThongTinSDInThanhVien(int MaTV)
    {
        List list;
        session.beginTransaction();
        Query q = session.createQuery("FROM ThongTinSD WHERE MaTV = :MaTV");
        q.setParameter("MaTV", MaTV);
        list = q.list();
        session.getTransaction().commit();
        return list;
    }
    
    public List getThongTinSDInThietBi(int MaTB)
    {
        List list;
        session.beginTransaction();
        Query q = session.createQuery("FROM ThongTinSD WHERE MaTB = :MaTB");
        q.setParameter("MaTB", MaTB);
        list = q.list();
        session.getTransaction().commit();
        return list;
    }
    public void add(ThongTinSD obj)
    {
        session.save(obj);
        session.beginTransaction();
        session.getTransaction().commit();
    }
    public void update(ThongTinSD obj)
    {
        session.update(obj);
    }
    public void delete(ThongTinSD obj)
    {
        session.delete(obj);
    }
}
