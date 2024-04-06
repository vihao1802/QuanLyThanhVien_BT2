/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
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
    public List<ThongTinSD> getThietBiDangMuon() {
        String query = "FROM ThongTinSD WHERE TGMuon IS NOT NULL AND TGTra IS NULL";

        session.beginTransaction();

        Query<ThongTinSD> queryResult = session.createQuery(query, ThongTinSD.class);
        List<ThongTinSD> danhSachThietBiDangMuon = queryResult.list();

        session.getTransaction().commit();

        return danhSachThietBiDangMuon;
    }


    public List<ThongTinSD> getThietBiDangMuonTrongNgay(Date ngayCanThongKe) {
        String query = "FROM ThongTinSD WHERE TGMuon IS NOT NULL AND TGTra IS NULL " +
                       "AND DATE(TGMuon) = :ngayCanThongKe";
        session.beginTransaction();
        Query<ThongTinSD> queryResult = session.createQuery(query, ThongTinSD.class);
        queryResult.setParameter("ngayCanThongKe", ngayCanThongKe);
        List<ThongTinSD> danhSachThietBiDangMuon = queryResult.list();
        session.getTransaction().commit();
        return danhSachThietBiDangMuon;
    }
     public List<ThongTinSD> getThietBiDangMuonTrongThang(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); 
        Date startDate = calendar.getTime(); 
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1); 
        Date endDate = calendar.getTime(); 
        session.beginTransaction();

        String query = "FROM ThongTinSD WHERE TGMuon IS NOT NULL AND TGTra IS NULL " +
                       "AND TGMuon BETWEEN :startDate AND :endDate";
        Query<ThongTinSD> queryResult = session.createQuery(query, ThongTinSD.class);
        queryResult.setParameter("startDate", startDate);
        queryResult.setParameter("endDate", endDate);

        List<ThongTinSD> danhSachThietBiDangMuon = queryResult.list();

        session.getTransaction().commit();
        return danhSachThietBiDangMuon;
    }
     public List<ThongTinSD> getThietBiDangMuonTrongNam(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1); 
        Date startDate = calendar.getTime();
        calendar.set(year, Calendar.DECEMBER, 31);
        Date endDate = calendar.getTime();

        session.beginTransaction();

        String query = "FROM ThongTinSD WHERE TGMuon IS NOT NULL AND TGTra IS NULL " +
                       "AND TGMuon BETWEEN :startDate AND :endDate";
        Query<ThongTinSD> queryResult = session.createQuery(query, ThongTinSD.class);
        queryResult.setParameter("startDate", startDate);
        queryResult.setParameter("endDate", endDate);

        List<ThongTinSD> danhSachThietBiDangMuon = queryResult.list();

        session.getTransaction().commit();
        return danhSachThietBiDangMuon;
    }
     public List<ThongTinSD> getThongTinSDTrongKhoangThoiGian(Date startDate, Date endDate) {
        session.beginTransaction();

        String query = "FROM ThongTinSD WHERE TGMuon IS NOT NULL AND TGTra IS NULL " +
                       "AND TGMuon BETWEEN :startDate AND :endDate";
        Query<ThongTinSD> queryResult = session.createQuery(query, ThongTinSD.class);
        queryResult.setParameter("startDate", startDate);
        queryResult.setParameter("endDate", endDate);

        List<ThongTinSD> danhSachThongTinSD = queryResult.list();

        session.getTransaction().commit();
        return danhSachThongTinSD;
    }

}
