package myhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.Survey;
import myhibernate.model.Worker;
import myhibernate.util.HibernateUtil;

public class WorkerDao {
	public List<Worker> getWorkersForCompany(String CompanyName)	{
		Survey survey = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            List<Worker> allworkers = new ArrayList();
            allworkers = (List<Worker>) session.createQuery("FROM Survey C WHERE C.CompanyName =: companyName ").setParameter("companyName", CompanyName).list();
                    
            // commit transaction
            transaction.commit();
            //session.close();
            
            	session.close();
            	return allworkers;
            	
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
	}

}
