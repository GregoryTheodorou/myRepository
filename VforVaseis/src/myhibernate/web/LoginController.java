package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import myhibernate.dao.UserDao;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  


public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    	response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out= response.getWriter();
        
       
        if (loginDao.validate(username, password)) {
        	HttpSession session=request.getSession();
        	session.setAttribute("UserID",username);
        	session.setAttribute("Role",loginDao.getUser(username).getRole());
        	if(loginDao.getUser(username).getRole().equals("admin"))	{
        		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");
                dispatcher.include(request, response);
        	}
        	else	{
	            RequestDispatcher dispatcher = request.getRequestDispatcher("LoginSuccess.jsp");
	            dispatcher.include(request, response);
        	}
        } else {
        	out.println("Wrong username or password.Try again");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
            //throw new Exception("Login not successful..");
        }
    }
}