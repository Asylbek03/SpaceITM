package com.example.spaceitm.util;


import com.example.spaceitm.model.Apod;
import com.example.spaceitm.model.Epic;
import com.example.spaceitm.model.NewsArticle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.spaceitm.util.Constants.*;

public class ApiCalls {
    Logger log = LoggerFactory.getLogger(ApiCalls.class);


    public Apod apod_ApiCall(double requestID) {
        Apod apodValue = new Apod();
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        String getapodCall = APOD_URL;

        log.info("RequestID: {} - APOD REQUEST - APOD Request API CALL : {}", requestID, getapodCall);
        HttpGet request = new HttpGet(getapodCall);
        try {
            response = client.execute(request);
            if (response != null) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String responseline = rd.readLine();
                log.info("RequestID: {} - APOD REQUEST - Get APOD Call : Response: {}", requestID, responseline);

                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(responseline, Map.class);

                apodValue.setApoddt((String) map.get("date"));
                apodValue.setApodExplation((String) map.get("explanation"));
                apodValue.setApodHdUrl((String) map.get("hdurl"));
                apodValue.setApodMedia_type((String) map.get("media_type"));
                apodValue.setApodTitle((String) map.get("title"));
                apodValue.setApodUrl((String) map.get("url"));

                return apodValue;
            } else {
                log.error("RequestID: {} - APOD REQUEST - Error Fetching response from APOD API", requestID);
                return null;
            }
        } catch (Exception e) {
            log.info("RequestID: {} - APOD REQUEST - Exception: Could not retrive APOD Information from API : {}", requestID, e.toString());
            return null;
        }


    }

    public Epic epic_ApiCall(double requestID) {
        Epic epicValue = new Epic();
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        String getEpicCall = EPIC_URL;


        log.info("RequestID: {} - EPIC REQUEST - EPIC Request API CALL: {}", requestID, getEpicCall);
        HttpGet request = new HttpGet(getEpicCall);
        try {
            response = client.execute(request);
            if (response != null) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String responseline = rd.readLine();
                log.info("RequestID: {} - EPIC REQUEST - Get EPIC Call : Response: {}", requestID, responseline);

                ObjectMapper mapper = new ObjectMapper();
                List<Map<String, Object>> list = mapper.readValue(responseline, new TypeReference<List<Map<String, Object>>>() {
                });
                if (!list.isEmpty()) {

                    Map<String, Object> map = list.get(0);
                    epicValue.setEpicDate((String) map.get("date"));
                    epicValue.setEpicImage((String) map.get("image"));
                    epicValue.setEpicCaption((String) map.get("caption"));
                }


                return epicValue;
            } else {
                log.error("RequestID: {} - EPIC REQUEST - Error Fetching response from EPIC API", requestID);
                return null;
            }
        } catch (Exception e) {
            log.info("RequestID: {} - EPIC REQUEST - Exception: Could not retrieve EPIC Information from API : {}", requestID, e.toString());
            return null;
        }
    }


    public String marsrover_ApiCall(double requestID) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        String getMarsRoverCall = MARS_ROVER_URL;

        log.info("RequestID: {} - MARS_ROVER REQUEST - MARS_ROVER Request API CALL : {}", requestID, getMarsRoverCall);
        HttpGet request = new HttpGet(getMarsRoverCall);
        try {
            response = client.execute(request);
            if (response != null) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String responseline = rd.readLine();
                //log.info("RequestID: {} - MARS_ROVER REQUEST - Get MARS_ROVER Call : Response: {}", requestID, responseline);


                return responseline;

            } else {
                log.error("RequestID: {} - MARS_ROVER REQUEST - Error Fetching response from MARS_ROVER API", requestID);
                return null;
            }
        } catch (Exception e) {
            log.info("RequestID: {} - MARS_ROVER REQUEST - Exception: Could not retrive MARS_ROVER Information from API : {}", requestID, e.toString());
            return null;
        }


    }


    public String news_ApiCall(double requestID) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response;

        log.info("RequestID: {} - NEWS REQUEST - News Request API CALL : {}", requestID, News_Api_URL);

        try {
            HttpGet request = new HttpGet(News_Api_URL);
            response = client.execute(request);

            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                EntityUtils.consume(response.getEntity()); // Ensure response entity is fully consumed
                log.info("RequestID: {} - NEWS REQUEST - Get News Call : Response: {}", requestID, result);
                return result.toString();
            } else {
                log.error("RequestID: {} - NEWS REQUEST - Error Fetching response from News API. Status code: {}",
                        requestID, response != null ? response.getStatusLine().getStatusCode() : "unknown");
                return null;
            }
        } catch (Exception e) {
            log.error("RequestID: {} - NEWS REQUEST - Exception: Could not retrieve News Information from API : {}",
                    requestID, e.toString());
            return null;
        } finally {
            client.getConnectionManager().shutdown();
        }
    }


}