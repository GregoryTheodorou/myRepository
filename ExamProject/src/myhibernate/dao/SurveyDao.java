package myhibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.Survey;
import myhibernate.util.HibernateUtil;

public class SurveyDao {
	 public int saveSurvey(Survey survey) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // save the student object
	            session.save(survey);
	            // commit transaction
	            transaction.commit();
	            session.close();
	            return 0;
	        } catch (Exception e) {
	        	
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return 1;
	        }
	    }
	 
	 public List<Survey> findSurvey(String game) {
		 	Survey survey = null;
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // save the student object
	            List<Survey> allreviews = new ArrayList();
	            allreviews = (List<Survey>) session.createQuery("FROM Survey C WHERE C.game =: gameName ").setParameter("gameName", game).list();
	                    
	            // commit transaction
	            transaction.commit();
	            //session.close();
	            
	            	session.close();
	            	return allreviews;
	            	
	            
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return null;
	    }
	 public List<Survey> searchByGenre(String genre)	{
		
		 Survey survey = null;
			Transaction transaction = null;
			try (Session ses = HibernateUtil.getSessionFactory().openSession()) {
				transaction = ses.beginTransaction();
				
				
				List allreviews = new ArrayList();
			    allreviews = (List<Survey>)ses.createQuery("from Survey where genre=: genreName").setParameter("genreName", genre).list();
			   	
			    transaction.commit();
			    ses.close();
			    
			    return allreviews;
			} 
			catch (Exception e) {
			   if (transaction != null) {
	                transaction.rollback();
	            }
				e.printStackTrace();
			}
			return null;
	 }
}
