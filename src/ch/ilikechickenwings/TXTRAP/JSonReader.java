package ch.ilikechickenwings.TXTRAP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSonReader {

	public JSONObject readJSON(String str){
		
		  JSONParser parser = new JSONParser();
		  
	        try {
	 
	            Object obj = parser.parse(new BufferedReader(new InputStreamReader((InputStream)(getClass().getResourceAsStream(str)))));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	            return jsonObject;
	        }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return null;
	        
		}
	
	
}
