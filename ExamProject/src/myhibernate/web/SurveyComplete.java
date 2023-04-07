package myhibernate.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Servlet implementation class SurveyComplete
 */
//@WebServlet("/SurveyComplete")
public class SurveyComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        out.println("Product has been logged in succesfully!<br>");
         String username = request.getParameter("username");
		 String game = request.getParameter("game");
		 String genre = request.getParameter("genre");
		 String playedtime = request.getParameter("playedtime");
		 String description = request.getParameter("description");
		 String likes = request.getParameter("likes");
		 String dislikes = request.getParameter("dislikes");
		 String recommended = request.getParameter("recommend");
		 
		 out.println("<br>username:"+username);
		 out.println("<br>game:"+game);
		 out.println("<br>genre:"+genre);
		 out.println("<br>playedtime:"+playedtime);
		 out.println("<br>description:"+description);
		 out.println("<br>likes:"+likes);
		 out.println("<br>dislike:"+dislikes);
		 out.println("<br>recommend:"+recommended);
        
      }  
}
