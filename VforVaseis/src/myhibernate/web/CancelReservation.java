package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myhibernate.dao.ReservationDao;
import myhibernate.dao.UserDao;
import myhibernate.model.Reservation;
import myhibernate.model.User;

/**
 * Servlet implementation class CancelReservation
 */

public class CancelReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private ReservationDao reservationDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelReservation() {
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
		response.setContentType("text/html");
	 	PrintWriter out= response.getWriter();
	 	
	 	String name = request.getParameter("restaurant");
	 	
	 	HttpSession session=request.getSession();
	 	 userDao = new UserDao();
	 	 reservationDao = new ReservationDao();
	 	User user = userDao.getUser((String)session.getAttribute("UserID"));//pairnw user class
	 	
	 	Iterator<Reservation> it = user.getReservation().iterator();
		Reservation reservation = new Reservation();
		while(it.hasNext())	{
			reservation = it.next();
			if(reservation.getRestaurant().getName().equals(name))	{
				reservationDao.deleteReservation(reservation);
			}
		}
		out.println("Deleted reservation for Restaurant: " +name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CancelReservation.jsp");
	    dispatcher.include(request, response);
	}
	
	
}
