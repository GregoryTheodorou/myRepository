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
 * Servlet implementation class RegisterRestaurant
 */
@WebServlet("/RegisterRestaurant")
public class RegisterRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantDao registerDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() {
        registerDao = new RestaurantDao();
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
	        	String name = request.getParameter("name");
	            String type = request.getParameter("type");
	            String description = request.getParameter("description");
	            String menu = request.getParameter("menu");
	            String contactphone = request.getParameter("contactnumber");
	            String address = request.getParameter("address");
	            String city = request.getParameter("city");
	            String tables = request.getParameter("tables");
	            String capacity = request.getParameter("capacity");
	            
	            if (name == "" || type == "" || description=="" || menu =="" || contactphone.length() != 10 || city == "" || address =="" || capacity == "" || tables == "") {
            		out.println("All fields are required,check contact number");
                	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminRestaurant.jsp");
                    dispatcher.include(request, response);
	    		}else {
	    			Restaurant temp = registerDao.getRestaurant(name);
	    			

	    	        if(registerDao.findPhone(contactphone)) {
	    	        	out.println("Contact phone already exists");
	                	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminRestaurant.jsp");
	                    dispatcher.include(request, response);
	    	        }else if(temp!=null) {
	    				out.println("Restaurant already exists,change name");
	                	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminRestaurant.jsp");
	                    dispatcher.include(request, response);
	    			}else {
			            Restaurant rest = new Restaurant();
			            rest.setCity(city);
			            rest.setName(name);
			            rest.setType(type);
			            rest.setMenu(menu);
			            rest.setContactPhone(contactphone);
			            rest.setAddress(address);
			            rest.setDescription(description);
			            rest.setDescription(description);
			            rest.setCapacity(Integer.parseInt(capacity));
			            rest.setTables(Integer.parseInt(tables));
			            
			            int result=registerDao.saveRestaurant(rest);
			            if (result ==1) {
			            	out.println("Something went wrong");
			            	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminRestaurant.jsp");
			                dispatcher.include(request, response);
			            }else if(result ==0) {
			            	out.println("Register restaurant succeed");
			            	RequestDispatcher dispatcher = request.getRequestDispatcher("AdminRestaurant.jsp");
			                dispatcher.include(request, response);
			            }
	    			}
	    		}
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

}