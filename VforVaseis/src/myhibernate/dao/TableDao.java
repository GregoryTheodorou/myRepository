package myhibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.Tables;
import myhibernate.util.HibernateUtil;

public class TableDao {
	public int saveTable(Tables table)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(table);
            // commit transaction
            transaction.commit();
            return 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 1;
        }
	}
}