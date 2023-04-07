package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;

import myhibernate.dao.*;
import myhibernate.model.*;

/**
 * Servlet implementation class RegisterReservation
 */

public class RegisterReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReservationDao reservationdao = new ReservationDao();
		PrintWriter out= response.getWriter();
	 	response.setContentType("text/html");
    	String restaurant = request.getParameter("restaurant");
    	LocalDateTime rdatetime;
    	 String guestsnumber = request.getParameter("guestsnumber");
    	 LocalDateTime currentDatetime =  LocalDateTime.now().plusHours(2);
    	 String datetime = request.getParameter("rdatetime") + "T" + request.getParameter("time");
    	try {
         rdatetime = LocalDateTime.parse(datetime).plusHours(2);
         //System.out.println("phra 1 :"+restaurant+"duo :"+rdatetime+"tria :"+guestsnumber);
         
         RestaurantDao resDao = new RestaurantDao(); 
         Restaurant res = resDao.getRestaurant(restaurant);
         
         if(res == null)	{
      	   out.println("Pick a restaurant from the list not at random");
      	   RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
             dispatcher.include(request, response);
         }
         else if(guestsnumber == null || Integer.parseInt(guestsnumber) == 0)	{
      	   out.println("You must fill the number of guests field");
      	   RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
             dispatcher.include(request, response);
         }
         else if(rdatetime.isBefore(currentDatetime))	{
        	 out.println("You must choose a future date time.");
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
             dispatcher.include(request, response);
         }
         else	{

             User user = new User();
             UserDao userdao = new UserDao();
             
  	       HttpSession session=request.getSession();
             user = userdao.getUser((String)session.getAttribute("UserID"));
             if (user== null) {
             	out.println("You need to log in");
             	RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
                dispatcher.include(request, response);
             }
      	   Reservation reservation = new Reservation();
      	   ReservationId reservationId = new ReservationId(user.getCustomerID(),res.getResID());
      	   reservation.setUser(user);
      	   reservation.setRestaurant(res);
      	   reservation.setGuestsNumber(guestsnumber);
      	   reservation.setRDateTime(rdatetime);
      	   reservation.setReservationId(reservationId);
      	   
      	   int result =reservationdao.findReservation(reservationId);
      	   if(result == 0)	{
	    	   int counter=0;
	  		   Iterator<Reservation> it = res.getReservation().iterator();
	  		   while (it.hasNext())	{
	  			   if(it.next().getRDateTime().isEqual(reservation.getRDateTime()))	{
	  				   counter++;
	  			   }
	  		   }
	  		   if(res.getTables()-counter ==0) {
		  	      	 out.println("That slot is full choose another time");
		  	      	 RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
	  	             dispatcher.include(request, response);
	  		   }else {	
		      	   reservationdao.saveReservation(reservation);
		      	   out.println("Success");
		      	   RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
		             dispatcher.include(request, response);
	  		   }
      	   }
      	   else	if(result == 1){
      		   
      		   Reservation resTemp = reservationdao.getReservation(reservationId);
      		   int counter=0;
    		   Iterator<Reservation> it = res.getReservation().iterator();
    		   while (it.hasNext())	{
    			   Reservation temp = it.next();
    			   if(temp.getRDateTime().isEqual(resTemp.getRDateTime()) && !(temp.getReservationId().equals(reservationId)))	{
    				   counter++;
    				   System.out.println("counte ="+counter);
    			   }
    		   }
    		   if(res.getTables()-counter ==0) {
    			 System.out.println(res.getTables());
  	  	      	 out.println("That slot is full choose another time");
  	  	      	 RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
    	             dispatcher.include(request, response);
    		   }else {	
	      		   LocalDateTime temp = reservation.getRDateTime().truncatedTo(ChronoUnit.HOURS).plusHours(2);
	      		   
	      		   
	      		   
	      		   if(currentDatetime.isAfter(temp))	{
	      			 reservationdao.saveOrUpdateReservation(reservation);
	      			 
	      			 
		  	      	 out.println("Made a new one");    //ignore to palio
		  	      	 RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
	  	             dispatcher.include(request, response);
	      		   }
	      		   else	{
	      			 
	      			   
		      		 reservationdao.saveOrUpdateReservation(reservation);
			      	 out.println("Your old one changed");
			      	 RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
			         dispatcher.include(request, response);
	      		   }
    		   }
      	   }
         }
    	}catch(Exception e) {
    		out.println("Date Time error");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReservation.jsp");
            dispatcher.include(request, response);
            e.printStackTrace();
    	}
        
        
        
	}

}
