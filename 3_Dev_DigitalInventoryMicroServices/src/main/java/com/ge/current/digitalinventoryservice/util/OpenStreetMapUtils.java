package com.ge.current.digitalinventoryservice.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class OpenStreetMapUtils {

//    public final static Logger log = Logger.getLogger("OpenStreeMapUtils");

    private static OpenStreetMapUtils instance = null;
    private JSONParser jsonParser;

    public OpenStreetMapUtils() {
        jsonParser = new JSONParser();
    }

    public static OpenStreetMapUtils getInstance() {
        if (instance == null) {
            instance = new OpenStreetMapUtils();
        }
        return instance;
    }

    private String getRequest(String url) throws Exception {

         URL obj = new URL(url);
         HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.connect();
        if (con.getResponseCode() != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        in.close();
        	con.disconnect();
        return response.toString();
    }
    
    
    

    public Map<String, Double> getCoordinates(String address) {
    	
        Map<String, Double> res;
        StringBuffer query;
        
       //Properties sysProperties = System.getProperties();
	  //  sysProperties.put("proxySet", "true");
		//sysProperties.put("http.proxyHost", "sjc1intproxy01.crd.ge.com");
	//	sysProperties.put("http.proxyPort", "8080");

		
        String[] split = address.split(" ");
        String queryResult = null;

        query = new StringBuffer();
        res = new HashMap<String, Double>();
        
       
        query.append("http://nominatim.openstreetmap.org/search?q=");

        if (split.length == 0) {
            return null;
        }

        for (int i = 0; i < split.length; i++) {
            query.append(split[i]);
            if (i < (split.length - 1)) {
                query.append("+");
            }
        }
        query.append("&format=json&addressdetails=1");

//        log.debug("Query:" + query);
   //     System.out.println("Query:" + query);
        try {
            queryResult = getRequest(query.toString());
        } catch (Exception e) {
//            log.error("Error when trying to get data with the following query " + query);
        	e.printStackTrace();
        	System.out.println("Error when trying to get data with the following query " + query);
        }

        if (queryResult == null) {
            return null;
        }

        Object obj = JSONValue.parse(queryResult);
//        log.debug("obj=" + obj);
    //    System.out.println("obj=" + obj);

        if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            if (array.size() > 0) {
                JSONObject jsonObject = (JSONObject) array.get(0);

               String lon = (String) jsonObject.get("lon");
               String lat = (String) jsonObject.get("lat");
//                log.debug("lon=" + lon);
        //        System.out.println("lon=" + lon);
//                log.debug("lat=" + lat);
         //       System.out.println("lat=" + lat);
                res.put("lon", Double.parseDouble(lon));
                res.put("lat", Double.parseDouble(lat));

            }
        }

        return res;
    }
}
