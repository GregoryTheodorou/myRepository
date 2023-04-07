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

import myhibernate.model.User;

public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao registerDao;

    public void init() {
        registerDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        	PrintWriter out= response.getWriter();
		 	response.setContentType("text/html");
        	String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            String mphone = request.getParameter("mphone");
            
            if (username == "" || password == "" || fname=="" || lname =="" || email == "" || city == "" || mphone.length() != 10) {
            	out.println("All fields are required,check phone number");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
                dispatcher.include(request, response);
    		}else {
	            User customer = new User();
	            
	            User temp = new User();
	            
	            temp = (User) registerDao.getUser(username);
	            if(temp != null) {
	            	out.println("User already exists,try another username");
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
	                dispatcher.include(request, response);
	            }else {
	            	customer.setCity(city);
	                customer.setUsername(username);
	                customer.setEmail(email);
	                customer.setFirstName(fname);
	                customer.setMobilePhone(mphone);
	                customer.setPassword(password);
	                customer.setLastName(lname);
	                customer.setRole("user");
	                if(registerDao.findPhone(mphone)) {
	                	out.println("Phone already exists,try another mobile");
		            	RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
		                dispatcher.include(request, response);
	                }else {
	                
		                int result=registerDao.saveUser(customer);
		                if (result ==1) {
		                	out.println("Register went wrong");
		                	RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
		                    dispatcher.include(request, response);
		                }else if(result ==0) {
		                	out.println("Register Succeed");
		                	RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
		                    dispatcher.include(request, response);
		                }
	                }
	            }
    		}
        	
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}