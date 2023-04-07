package myhibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.User;
import myhibernate.util.HibernateUtil;

public class UserDao {

    public int saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
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
    
    public int deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(user);
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
    public boolean validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User C WHERE C.username =: userName").setParameter("userName", userName)
                    .uniqueResult();

            if (user != null && user.getPassword().equals(password) && user.getUsername().equals(userName)) {

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
        }
        return false;
    }
    
    public User getUser(String userName) {
    	User user = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
         transaction = session.beginTransaction();
         user = (User) session.createQuery("FROM User C WHERE C.username =: userName").setParameter("userName", userName)
                 .uniqueResult();

         transaction.commit();
         session.close();
         return user;
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
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User C WHERE C.mobilePhone =: mobile").setParameter("mobile",mobilePhone)
                    .uniqueResult();

            if (user != null) {

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

}