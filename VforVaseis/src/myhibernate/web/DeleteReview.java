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
import java.util.*;

import myhibernate.dao.*;
import myhibernate.model.*;
/**
 * Servlet implementation class DeleteReview
 */

public class DeleteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	private ReviewDao reviewDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReview() {
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
		HttpSession session=request.getSession();
		if(session.getAttribute("Role").equals("admin"))	{
			AdminDelete(request,response);
		}
		else	{
			UserDelete(request,response);
		}
	}
		
	private void AdminDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	 	response.setContentType("text/html");
	 	PrintWriter out= response.getWriter();
		
		String user_res = request.getParameter("username");
		
		
		int koma =user_res.lastIndexOf(",");
		
		String username = user_res.substring(0, koma);
		String restaurant = user_res.substring(koma+1, user_res.length());
		System.out.println("phra user :" + username + " rest "+ restaurant);
		reviewDao = new ReviewDao();
		userDao = new UserDao();  
		
		User user = userDao.getUser(username);
		Iterator<Review> it = user.getReview().iterator();
		Review review = new Review();
		while(it.hasNext())	{
			review = it.next();
			if(review.getRestaurant().getName().equals(restaurant))	{
				reviewDao.deleteReview(review);
			}
		}
		out.println("Deleted review for User: " +username);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchReview.jsp");
	    dispatcher.include(request, response);
	}
	
	private void UserDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
	 	response.setContentType("text/html");
	 	
		String name = request.getParameter("restaurant");
		
		HttpSession session=request.getSession();
		userDao = new UserDao();  
		User user = userDao.getUser((String)session.getAttribute("UserID"));//pairnw user class
		
		reviewDao = new ReviewDao();
		
		Iterator<Review> it = user.getReview().iterator();
		Review review = new Review();
		while(it.hasNext())	{
			review = it.next();
			if(review.getRestaurant().getName().equals(name))	{
				reviewDao.deleteReview(review);
			}
		}
		
		out.println("Deleted review for restaurant: " +name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MyReviews.jsp");
	    dispatcher.include(request, response);
	}
}
