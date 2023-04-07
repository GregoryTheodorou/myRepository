package myhibernate.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import myhibernate.model.*;
import myhibernate.util.HibernateUtil;


public class ReservationDao {
	public int saveReservation(Reservation reservation)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(reservation);
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
	
	public int findReservation(ReservationId reservationId)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Reservation reservation= new Reservation();
            reservation = (Reservation) session.get(Reservation.class,reservationId );
            
           // reservation = (Reservation) session.createQuery("FROM Reservation C WHERE C.customer =: customerID AND C.restaurant =: restaurantID").setParameter("customerID",customerID).setParameter("restaurantID", resID)
                   // .uniqueResult();
            // commit transaction
            transaction.commit();
            if(reservation == null) {		// an den yparxei epistrefei 0
            	session.close();
                return 0;
            }
            session.close();
            return 1; //an yparxei
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
	}
	
	public Reservation getReservation(ReservationId reservationId)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Reservation reservation= new Reservation();
            reservation = (Reservation) session.get(Reservation.class,reservationId );
            
           // reservation = (Reservation) session.createQuery("FROM Reservation C WHERE C.customer =: customerID AND C.restaurant =: restaurantID").setParameter("customerID",customerID).setParameter("restaurantID", resID)
                   // .uniqueResult();
            // commit transaction
            transaction.commit();
            session.close();
            if(reservation == null) {		// an den yparxei epistrefei 0
                return null;
            }
            return reservation; //an yparxei
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
	}
	
	
	public int deleteReservation(Reservation reservation)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(reservation);
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
	public int saveOrUpdateReservation(Reservation reservation)	{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(reservation);
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