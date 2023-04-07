package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhibernate.dao.*;
import myhibernate.model.*;

/**
 * Servlet implementation class DeleteUser
 */

public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
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
		PrintWriter out= response.getWriter();
	 	response.setContentType("text/html");
    	String username = request.getParameter("username");
    	userDao = new UserDao();
    	User user = userDao.getUser(username);
    	int check =userDao.deleteUser(user);
    	if(check == 1)	{
    		System.out.println("Could not delete");
    	}
    	out.println("Deleted user: " +username);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteUser.jsp");
        dispatcher.include(request, response);
	}

}
