package myhibernate.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.*;
import myhibernate.util.HibernateUtil;


public class ReviewDao {
	public int saveReview(Review review)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(review);
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
	
	public int findReview(ReviewId reviewId)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Review review= new Review();
            review = (Review) session.get(Review.class, reviewId);
            /*
            review = (Review) session.createQuery("FROM Review C WHERE C.customer =: customerID AND C.restaurant =: restaurantID").setParameter("customerID",customerID).setParameter("restaurantID", resID)
                    .uniqueResult();*/
            // commit transaction
            transaction.commit();
            session.close();
            if(review == null) {		// an den yparxei epistrefei 0
                return 0;
            }
            return 1; //an yparxei
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
	}
	
	public int deleteReview(Review review)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(review);
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
	public int saveOrUpdateReview(Review review)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(review);
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
	
}