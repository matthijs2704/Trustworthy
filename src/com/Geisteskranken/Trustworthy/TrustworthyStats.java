package com.Geisteskranken.Trustworthy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TrustworthyStats {
	public static String MCBANS = "mcbans";
	public static String MCBOUNCER = "mcbouncer";
	public static String MCBLOCKIT = "mcblockit";
	public static String MINEBANS = "minebans";
	public static String GLIZER = "glizer";

	public static int getStats(String playername,String servicename){
		URL url;
		try {
			url = new URL("http://www.fishbans.com/api/stats/"+playername+"/"+servicename+"/");
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(builder.toString());
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject bans = (JSONObject) jsonObject.get("stats");
			if(bans == null){
				return 0;
			}
			JSONObject service = (JSONObject) bans.get("service");
			long s = 0;
			if(service.get(servicename) != null) s = (Long) service.get(servicename);
			return (int) s;
		} catch (MalformedURLException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		} catch (ParseException e) {
			return 0;
		}
		
	}
}
