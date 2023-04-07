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

import myhibernate.dao.UserDao;

import myhibernate.dao.*;
import myhibernate.model.*;

/**
 * Servlet implementation class RegisterReview
 */
public class RegisterReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewDao reviewDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() {
        reviewDao = new ReviewDao();
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
		 try {
			 	PrintWriter out= response.getWriter();
			 	response.setContentType("text/html");
	        	String restaurantName = request.getParameter("restaurant");
	            String description = request.getParameter("description");
	            if (restaurantName == "" || description=="") {
	            		response.sendRedirect("RegisterReview.jsp");
	    		}
	            
	            User user = new User();
	            UserDao userdao = new UserDao();
	            Restaurant restaurant = new Restaurant();
	            RestaurantDao restaurantdao = new RestaurantDao();
	            
	            
	        	HttpSession session=request.getSession();
	        	restaurant = restaurantdao.getRestaurant(restaurantName);
	            user = userdao.getUser((String)session.getAttribute("UserID"));
	            if(restaurant == null) {
	            	out.println(restaurantName +" doesnt exist");
	            	response.sendRedirect("RegisterReview.jsp");
	            }else if (user== null) {
	            	out.println("You need to log in");
	            	response.sendRedirect("RegisterReview.jsp");
	            }
	            Review review = new Review();
	            
	            
	            
	            
	            //ReviewId reviewId= new ReviewId(user.getCustomerID(),restaurant.getResID());
	            
	            
	            review.setReview(description);
	            review.setUser(user);
	            review.setRestaurant(restaurant);
	            ReviewId reviewId = new ReviewId(user.getCustomerID(),restaurant.getResID());
	            review.setReviewId(reviewId);


	            int result = reviewDao.findReview(reviewId);
	            int check;
	            
	            if(result == 0) { //den yparxei
	            	check = reviewDao.saveReview(review);
	            	if(check ==0) {
	    	            out.println("Register succeded ,Do you want to register or change another review?");
		            	RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReview.jsp");
		                dispatcher.include(request, response);
	            	}else if(check == 1) {
	            		out.println("Something went horribly wrong");
		            	RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReview.jsp");
		                dispatcher.include(request, response);
	            	}
	            }
	            else if(result == 1) { //yparxei
	            	check = reviewDao.saveOrUpdateReview(review);
	            	if(check ==0) {
		            	out.println("Your previous review has changed");
		            	RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReview.jsp");
		                dispatcher.include(request, response);
	            	}else if(check == 1) {
	            		out.println("Something went horribly wrong");
		            	RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterReview.jsp");
		                dispatcher.include(request, response);
	            	}
	            }
	            
	        	
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

}