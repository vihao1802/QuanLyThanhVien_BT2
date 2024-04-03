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
public class XuLyDAL {
    Session session;
    
    public XuLyDAL()
    {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public XuLy getXuLy(int XuLyID)
    {
        XuLy obj;
        session.beginTransaction();
        obj = session.get(XuLy.class, XuLyID);
        session.getTransaction().commit();
        return obj;
        
    }
    public List getXuLyInThanhVien(int MaTV)
    {
        List list;
        session.beginTransaction();
        Query q = session.createQuery("FROM XuLy WHERE MaTV = :MaTV");
        q.setParameter("MaTV", MaTV);
        list = q.list();
        session.getTransaction().commit();
        return list;
    }
    
    public XuLy getXuLyThanhVienDangViPham(int MaTV) {
        session.beginTransaction();
        Query q = session.createQuery("FROM XuLy WHERE MaTV = :MaTV AND TrangThaiXL = :TrangThaiXL");
        q.setParameter("MaTV", MaTV);
        q.setParameter("TrangThaiXL", 0);
        XuLy xl = (XuLy) q.uniqueResult();
        session.getTransaction().commit();
        return xl;
    }
    
    public void add(XuLy obj)
    {
        session.save(obj);
    }
    public void update(XuLy obj)
    {
        session.update(obj);
    }
    public void delete(XuLy obj)
    {
        session.delete(obj);
    }
}
