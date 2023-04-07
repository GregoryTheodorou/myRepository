package myhibernate.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import myhibernate.model.*;
import myhibernate.web.SearchByGenre;
import myhibernate.dao.SurveyDao;
/**
 * Servlet implementation class SearchByName
 */
//@WebServlet("/SearchByName")
public class SearchByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByName() {
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
		String name = request.getParameter("name");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Database func = new Database();
		String accesstoken = func.getToken();
		SurveyDao surveyDao = new SurveyDao();
		
		
		try {
			int checkvalidkey = func.validateToken(accesstoken);
			if(checkvalidkey == 1)	{
				accesstoken = func.getNewAccessToken();
				//func.setToken(accesstoken);
			}
			JSONArray arrayOfGames = func.getSimilarGames(name, accesstoken);
			if(arrayOfGames==null)	{
				out.println("Couldn't find similar games to that.");
				//System.out.println("Couldn't find similar games to that.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Recommendation.jsp");
		        dispatcher.include(request, response);
		        return;
			}
			Extra str = new Extra();
			List<Survey> list= new ArrayList();
			for(int i=0;i<arrayOfGames.length();i++)	{
				
				list =surveyDao.findSurvey(arrayOfGames.getJSONObject(i).getString("name"));
				Iterator<Survey> iterator = list.iterator();
				if(iterator.hasNext())	{
					Survey survey = iterator.next();
					
					str.Add(String.valueOf(i),survey.getLikes(),survey.getDislikes(),survey.getRecommend(),survey.getUserName());
					//System.out.println("ENA**"+str.toString());
				}
			}
			
			if(str.isEmpty() == -1)	{
				request.setAttribute("array", arrayOfGames.toString());
				request.setAttribute("myarray", "nothing");
				request.setAttribute("likes", "---");
				request.setAttribute("dislikes", "---");
				request.setAttribute("recommend", "---");
				request.setAttribute("user", "---");
			}
			else	{
				request.setAttribute("array", arrayOfGames.toString());
				request.setAttribute("myarray", str.toString());
				request.setAttribute("likes", "---");
				request.setAttribute("dislikes", "---");
				request.setAttribute("recommend", "---");
				request.setAttribute("user", "---");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SimilarGames.jsp");
        dispatcher.forward(request, response);
	}

}
