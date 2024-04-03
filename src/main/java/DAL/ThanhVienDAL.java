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
}
