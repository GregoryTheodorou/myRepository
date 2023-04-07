package myhibernate.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.crypto.Data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;

public class Database {
	private String accesstoken ;
	public String getToken()	{
		
		return accesstoken;
	}
	public Database()	{
		File myObj = new File("/home/user/Downloads/apache-tomcat-9.0.39/webapps/ExamProject/token.txt");
		 try {
	      Scanner myReader = new Scanner(myObj);
	      if (myReader.hasNextLine()) {
	         accesstoken = myReader.nextLine();
	        //System.out.println(data);
	        myReader.close();
	      }
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	/*public void setToken(String token)	{
		this.accesstoken = token;
	}*/
	public int validateToken(String token) throws IOException, JSONException	{
		/*String command = "curl -H \"Authorization: OAuth "+token+"\" https://id.twitch.tv/oauth2/validate";
		Process process = Runtime.getRuntime().exec(command);
		InputStream stream	=process.getInputStream();
		String result = convertStreamToString(stream);
		//System.out.println("Authorization~~~~"+result);
		JSONObject obj = new JSONObject(result);
		process.destroy();*/
		HttpGet httppost = new HttpGet("https://id.twitch.tv/oauth2/validate");
		httppost.setHeader("Authorization","OAuth ilu93evb7aslajowus40768ujzefbl");
		httppost.setHeader("Content-Type","text/plain");
		HttpClient httpclient = HttpClients.createDefault();
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		
		String result = convertStreamToString(instream);
		JSONObject obj = new JSONObject(result);
		if((obj.getInt("expires_in"))>0) {
			return 0;
		}
		return 1;
	}
	public String getNewAccessToken() throws ClientProtocolException, IOException, JSONException	{
		HttpPost httppost = new HttpPost("https://id.twitch.tv/oauth2/token?client_id=kz1jg9g8oonta76ei9t1ojwtqwg1aa&client_secret=jfd7ur3ix480wpkzuk3wtrd1tfmm1a&grant_type=client_credentials");
		HttpClient httpclient = HttpClients.createDefault();
		HttpResponse response = httpclient.execute(httppost);
		
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		String result = convertStreamToString(instream);
		JSONObject obj = new JSONObject(result);
		accesstoken = obj.getString("access_token");
		return obj.getString("access_token");
		// Request parameters and other properties.
		
	}
	public JSONArray getSimilarGames(String name,String accesstoken) throws UnsupportedOperationException, IOException, JSONException	{
		JSONObject game = findName(name,accesstoken);
		if( game != null)	{
			if(game.has("similar_games")) {
			JSONArray array = game.getJSONArray("similar_games");
			
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost2 = new HttpPost("https://api.igdb.com/v4/games");
			
	    	httppost2.setHeader("Client-ID"," kz1jg9g8oonta76ei9t1ojwtqwg1aa");
	    	httppost2.setHeader("Authorization","Bearer "+accesstoken);
	    	httppost2.setHeader("Content-Type","text/plain");
	    	String str ="fields *;where ";
	    	for(int i=0;i<array.length();i++)	{
	    		if(i<array.length()-1)	{
	    			str = str +"id ="+array.getInt(i)+" | ";
	    		}
	    		else	{
	    			str = str +"id ="+array.getInt(i)+";";
	    		}
	    	}

	    	httppost2.setEntity(new StringEntity(str));
	    	HttpResponse response2 = httpclient.execute(httppost2);
	    	HttpEntity entity = response2.getEntity();
	    	InputStream instream = entity.getContent();
	    	String string = convertStreamToString(instream);

	    	JSONArray newarray = new JSONArray(string);
	    	return newarray;
			}
		}
		else	{
			System.out.println("DN EIXE SIMILAR");
		}
		return null;
	}
	public JSONObject findName(String name,String accesstoken) throws UnsupportedOperationException, IOException, JSONException	{
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost2 = new HttpPost("https://api.igdb.com/v4/games");
		
    	httppost2.setHeader("Client-ID"," kz1jg9g8oonta76ei9t1ojwtqwg1aa");
    	httppost2.setHeader("Authorization","Bearer "+accesstoken);
    	httppost2.setHeader("Content-Type","text/plain");
    	httppost2.setEntity(new StringEntity("fields *;limit 1;where name ~*\""+name+"\"*;"));
    	HttpResponse response2 = httpclient.execute(httppost2);
    	//System.out.println(response2);
    	HttpEntity entity = response2.getEntity();
    	//System.out.println(entity);
    	InputStream instream = entity.getContent();
    	
    	//String string = convertStreamToString(instream);
    	JsonReader jsonReader = Json.createReader(instream);
		 javax.json.JsonArray array = jsonReader.readArray();
		 System.out.println(array.toString());
		 if(array.isEmpty())	{
			 return null;
		 }
		JsonObject temp = (JsonObject) array.getJsonObject(0);
		String string = temp.toString();
    	//System.out.println("ELAAAAA"+string);
    	JSONObject obj = new JSONObject(string);
    	
		return obj;
	}
	public int getGenreId(String str ,String accesstoken) throws ClientProtocolException, IOException, JSONException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("https://api.igdb.com/v4/genres");
		JsonObject temp;
    	httppost.setHeader("Client-ID"," kz1jg9g8oonta76ei9t1ojwtqwg1aa");
    	httppost.setHeader("Authorization","Bearer "+accesstoken);
    	httppost.setHeader("Content-Type","text/plain");
    	
		
		httppost.setEntity(new StringEntity("fields *;where name ~*\""+str+"\"*;"));
		
    	
    	HttpResponse response = httpclient.execute(httppost);
    	HttpEntity entity = response.getEntity();

    	InputStream instream = entity.getContent();

    	if(instream.available()==2)	{
    		return -1;
    	}
    	
    	javax.json.JsonArray array = convertStringToJason(instream);
 		
		temp =(JsonObject) array.getJsonObject(0);
 		
    	 //JSONObject temp = new JSONObject(result);
		 return temp.getInt("id");
	}
	public  javax.json.JsonArray convertStringToJason(InputStream stream)	{
		 JsonReader jsonReader = Json.createReader(stream);
		 javax.json.JsonArray array = jsonReader.readArray();
		
		return array;
	}
	
	public static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
