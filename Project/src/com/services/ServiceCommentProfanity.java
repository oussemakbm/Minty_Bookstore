/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;



/**
 *
 * @author DellXPS
 */
public class ServiceCommentProfanity {

    private static ServiceCommentProfanity INSTANCE;


    private static final String POST_URL = "https://neutrinoapi-bad-word-filter.p.rapidapi.com/bad-word-filter";

    

    private ServiceCommentProfanity() {

    }

    public static ServiceCommentProfanity getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceCommentProfanity();
        }

        return INSTANCE;
    }

    public String getCleanComment(String commentBody) throws Exception {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        
        
        con.setRequestProperty("x-rapidapi-key", "8ab149114dmsh80111528716ca1cp1857e9jsn2dd75a72da34");
        con.setRequestProperty("x-rapidapi-host", "neutrinoapi-bad-word-filter.p.rapidapi.com");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        String POST_PARAMS = "censor-character=*&content="+commentBody;
        
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // print result
            System.out.println(response.toString());
            JSONObject data = new JSONObject(response.toString());
            String censored_content = data.getString("censored-content");
            return censored_content;
        } else {
            System.out.println("POST request not worked");
            return "REQUEST_ERROR";
        }
        
    }

}
