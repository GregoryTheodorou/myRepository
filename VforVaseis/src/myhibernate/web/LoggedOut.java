package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import myhibernate.dao.UserDao;
import myhibernate.model.User;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  

public class LoggedOut extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false); //Fetch session object
        
        if(session!=null) //If session is not null
        {
            session.invalidate(); //removes all session attributes bound to the session
        	request.setAttribute("message", "You have logged out successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loggedOut.jsp");
            requestDispatcher.forward(request, response);
            System.out.println("Logged out");
        }
    }
    
}