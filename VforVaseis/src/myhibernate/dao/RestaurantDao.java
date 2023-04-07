package myhibernate.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.Restaurant;
import myhibernate.model.User;
import myhibernate.util.HibernateUtil;


public class RestaurantDao {
	public int saveRestaurant(Restaurant rest)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(rest);
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
	
	public Restaurant getRestaurant(String restaurantName) {
		Restaurant restaurant = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
        transaction = session.beginTransaction();
        restaurant = (Restaurant) session.createQuery("FROM Restaurant C WHERE C.name =: restaurantName").setParameter("restaurantName", restaurantName)
                .uniqueResult();

         transaction.commit();
         session.close();
         return restaurant;
        } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
        }
    }
	public boolean findPhone(String mobilePhone) {

        Transaction transaction = null;
        Restaurant restaurant = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            restaurant = (Restaurant) session.createQuery("FROM Restaurant C WHERE C.contactPhone =: mobile").setParameter("mobile",mobilePhone)
                    .uniqueResult();

            if (restaurant != null) {
                transaction.commit();
            	session.close();
                return true;
            }
            // commit transaction
            //session.createQuery("delete from Reservation").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
               transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
	
	public int deleteRestaurant(Restaurant rest)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(rest);
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