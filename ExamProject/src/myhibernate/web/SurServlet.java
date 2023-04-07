package myhibernate.web;
import java.io.*;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import myhibernate.dao.SurveyDao;
import myhibernate.model.Survey;
/**
 * Servlet implementation class SurServlet
 */

public class SurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyDao surveyDao;
	private Survey survey;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String username = request.getParameter("username");
		 String game = request.getParameter("game");
		 String genre = request.getParameter("genre");
		 String playedtime = request.getParameter("playedtime");
		 String description = request.getParameter("description");
		 String likes = request.getParameter("likes");
		 String dislikes = request.getParameter("dislikes");
		 String recommended = request.getParameter("recommend");
		 
		 
		 surveyDao = new SurveyDao();
		 survey = new Survey();
		 
		survey.setUserName(username);
		survey.setGame(game);
		survey.setGenre(genre);
		survey.setPlayedTime(playedtime);
		survey.setDescription(description);
		survey.setLikes(likes);
		survey.setDislikes(dislikes);
		survey.setRecommend(recommended);
		
		int res = surveyDao.saveSurvey(survey);
		if(res == 1)	{
			out.println("Something went wrong.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Survey.jsp");
            dispatcher.include(request, response);
		}
		else	{
			out.println("Suvey for game:" +game+ " was written on database successfully");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Survey.jsp");
            dispatcher.include(request, response);
		}
	}

}
