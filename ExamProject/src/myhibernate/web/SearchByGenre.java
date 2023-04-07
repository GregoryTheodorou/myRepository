package myhibernate.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.json.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
//import org.json.simple.JSONObject;
import org.json.*;
import org.apache.http.client.ClientProtocolException;
//import org.json.simple.parser.JSONParser;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import java.lang.Object.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.NameValuePair;

import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.auth.Scopes;
import com.mysql.cj.xdevapi.JsonArray;
import com.squareup.okhttp.*;
//import org.apache.xerces.util.URI;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import myhibernate.dao.*;
import myhibernate.model.*;
import java.util.Random;
/**
 * Servlet implementation class test
 */
//@WebServlet("/test")
public class SearchByGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByGenre() {
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
		PrintWriter out = response.getWriter();
		Database func = new Database();
		String genre = request.getParameter("genre");
		String accesstoken = func.getToken();
		HttpClient httpclient = HttpClients.createDefault();
		int genreid = -1;
		HttpEntity entity = null;
		response.setContentType("text/html");
		
		try {
			int checkvalidkey = func.validateToken(accesstoken);
			if(checkvalidkey == 1)	{
				accesstoken = func.getNewAccessToken();
				//func.setToken(accesstoken);
			}
			genreid = func.getGenreId(genre,accesstoken);
			
			if(genreid == -1)	{
				
				out.println("Could not find the genre you requested");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Recommendation.jsp");
		        dispatcher.include(request, response);
		        return;
			}
			
			SurveyDao surveyDao = new SurveyDao();
			List<Survey> list= new ArrayList();
			list = surveyDao.searchByGenre(genre);
			Iterator<Survey> iterator = list.iterator();
			if(iterator.hasNext())	{
				//while (iterator.hasNext())	{
					Survey survey = iterator.next();
					JSONObject temp = func.findName(survey.getGame(),accesstoken);
					request.setAttribute("likes", survey.getLikes());
					request.setAttribute("dislikes", survey.getDislikes());
					request.setAttribute("recommend", survey.getRecommend());
					request.setAttribute("user", survey.getUserName());
					request.setAttribute("obj",(JSONObject)temp);
				//}
			}
			else	{
				HttpPost httppost2 = new HttpPost("https://api.igdb.com/v4/games");
		    	httppost2.setHeader("Client-ID"," kz1jg9g8oonta76ei9t1ojwtqwg1aa");
		    	httppost2.setHeader("Authorization","Bearer "+accesstoken);
		    	httppost2.setHeader("Content-Type","text/plain");
		    	httppost2.setEntity(new StringEntity("fields *; limit 30;where genres=("+genreid+");"));
		    	 
		    	HttpResponse response2 = httpclient.execute(httppost2);
		    	System.out.println("response2~~~~~~"+response2);
		    	entity = response2.getEntity();
		    	try(InputStream instream2 = entity.getContent()){
		    		 String result = func.convertStreamToString(instream2);
		    		JSONArray array = new JSONArray(result);
		    		response.setContentType("text/html");
		    		//for(int i =0;i<array.size();i++)	{
		    			Random rand = new Random();
		    			int i = rand.nextInt(array.length());
		    			JSONObject temp =(JSONObject) array.getJSONObject(i);
		    			request.setAttribute("obj",(JSONObject)temp);
		    			request.setAttribute("likes", "---");
						request.setAttribute("dislikes", "---");
						request.setAttribute("recommend", "---");
						request.setAttribute("user", "---");
	    		}
	    	}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//out.println("telepsa.");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/RecReturn.jsp");
        dispatcher.forward(request, response);
	}
	
}
