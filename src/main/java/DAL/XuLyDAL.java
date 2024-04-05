package DAL;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.OptimisticLockException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class XuLyDAL {
    private static final Logger LOGGER = Logger.getLogger(XuLyDAL.class.getName());
    private final Session session;
    private Transaction transaction;

    public XuLyDAL() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public XuLy getXuLy(int XuLyID) {
        XuLy obj = null;
        try {
            beginTransaction();
            obj = session.get(XuLy.class, XuLyID);
            commitTransaction();
        } catch (Exception ex) {
            handleException("Error retrieving XuLy with ID: " + XuLyID, ex);
        }
        return obj;
    }

    public List<XuLy> getAllXuly() {
        List<XuLy> list = null;
        try {
            beginTransaction();
            Query<XuLy> q = session.createQuery("FROM XuLy", XuLy.class);
            list = q.list();
            commitTransaction();
        } catch (Exception ex) {
            handleException("Error retrieving all XuLy", ex);
        }
        return list;
    }

    public XuLy getXuLyThanhVienDangViPham(int MaTV) {
        XuLy xl = null;
        try {
            beginTransaction();
            Query<XuLy> q = session.createQuery("FROM XuLy WHERE MaTV = :MaTV AND TrangThaiXL = :TrangThaiXL", XuLy.class);
            q.setParameter("MaTV", MaTV);
            q.setParameter("TrangThaiXL", 0);
            xl = q.uniqueResult();
            commitTransaction();
        } catch (Exception ex) {
            handleException("Error retrieving XuLy for ThanhVien with ID: " + MaTV, ex);
        }
        return xl;
    }

    public void add(XuLy obj) {
        try {
            beginTransaction();
            session.save(obj);
            commitTransaction();
        } catch (Exception ex) {
            handleException("Error adding XuLy", ex);
        }
    }

public void update(XuLy obj) {
    Transaction transaction = null;
    try {
        transaction = session.beginTransaction();
        XuLy existingObj = session.get(XuLy.class, obj.getMaXL());
        if (existingObj != null) {
            // Check version before updating
//            if (existingObj.getVersion() == obj.getVersion()) {
                // Update existing object properties here
                existingObj.setHinhThucXL(obj.getHinhThucXL());
                existingObj.setNgayXL(obj.getNgayXL());
                existingObj.setSoTien(obj.getSoTien());
                existingObj.setTrangThaiXL(obj.getTrangThaiXL());
                
                session.update(existingObj); // Update the object in the database
                transaction.commit();
                System.out.println("XuLy object updated successfully.");
//            } else {
//                throw new OptimisticLockException("Concurrent update detected! Please refresh data.");
//            }
        } else {
            throw new IllegalArgumentException("Object to update not found!");
        }
    } catch (OptimisticLockException ex) {
        handleOptimisticLockException("Error updating XuLy", ex);
        if (transaction != null) {
            transaction.rollback();
        }
    } catch (Exception ex) {
        handleException("Error updating XuLy", ex);
        if (transaction != null) {
            transaction.rollback();
        }
    }
}






private void handleOptimisticLockException(String message, OptimisticLockException ex) {
        // Handle OptimisticLockException here (e.g., logging, error message)
        LOGGER.log(Level.SEVERE, message, ex);
    }
    private void beginTransaction() {
        transaction = session.beginTransaction();
    }

    private void commitTransaction() {
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    private void handleException(String message, Exception ex) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        LOGGER.log(Level.SEVERE, message, ex);
    }
}
