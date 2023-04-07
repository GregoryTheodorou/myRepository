package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhibernate.dao.UserDao;

import myhibernate.dao.RestaurantDao;
import myhibernate.model.Restaurant;



/**
 * Servlet implementation class DeleteRestaurant
 */

public class DeleteRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantDao registerDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRestaurant() {
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
		PrintWriter out= response.getWriter();
	 	response.setContentType("text/html");
    	String name = request.getParameter("restaurant");
    	registerDao = new RestaurantDao();
    	Restaurant res = registerDao.getRestaurant(name);
    	int check =registerDao.deleteRestaurant(res);
    	if(check == 1)	{
    		System.out.println("Could not delete");
    	}
    	out.println("Deleted Restauran: " +name);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminRestaurant.jsp");
        dispatcher.include(request, response);
	}

}
