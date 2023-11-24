package com.example.spaceitm.controller;

import com.example.spaceitm.model.*;
import com.example.spaceitm.util.ApiCalls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class NasaController {

    ApiCalls apiCallObj = new ApiCalls();
    Logger log = LoggerFactory.getLogger(NasaController.class);


    @RequestMapping(value = "/apod")
    public ModelAndView apod() throws JsonMappingException, JsonProcessingException, JSONException, ParseException
    {
        ModelAndView mav = null;
        double requestID =  Math.floor((Math.random() * 1000)*100) / 100;
        mav = new ModelAndView("apod");
        log.info("RequestID: {} - APOD REQUEST - STARTED", requestID);


        Apod apodResponse = apiCallObj.apod_ApiCall(requestID);

        log.info("RequestID: {} - APOD REQUEST - Response - {}", requestID, apodResponse.toString());
        mav.addObject("apodResponse",apodResponse);

        return mav;
    }

    @RequestMapping(value = "/epic")
    public ModelAndView epic() throws JsonMappingException, JsonProcessingException, JSONException, ParseException {
        ModelAndView mav = null;
        double requestID = Math.floor((Math.random() * 1000) * 100) / 100;
        mav = new ModelAndView("epic");

        log.info("RequestID: {} - EPIC REQUEST - STARTED", requestID);

        Epic epicResponse = apiCallObj.epic_ApiCall(requestID);


        if (epicResponse != null) {

            log.info("RequestID: {} - EPIC REQUEST - Response - {}", requestID, epicResponse.toString());

            mav.addObject("epicResponse", epicResponse);
        } else {
            log.error("RequestID: {} - EPIC REQUEST - Error fetching EPIC data", requestID);
            mav.addObject("error", "Error fetching EPIC data. Please try again.");
        }

        return mav;
    }



    @RequestMapping(value = "/mars")
    public ModelAndView mars() throws JsonMappingException, JsonProcessingException, JSONException, ParseException {
        ModelAndView mav = null;
        double requestID = Math.floor((Math.random() * 1000) * 100) / 100;
        mav = new ModelAndView("mars");
        String marsRoverResponse = apiCallObj.marsrover_ApiCall(requestID);
        //log.info("RequestID: {} - MARS_ROVER REQUEST - Response - {}", requestID, marsRoverResponse);
        List<MarsRoverPhotos> marsRoverPhotos = new ArrayList<>();

        JSONObject obj = new JSONObject(marsRoverResponse);

        JSONArray arr = obj.getJSONArray("photos");
        for (int j = 0; j < 50; j++) {
            //String post_id = arr.getJSONObject(i).getString("id");
            //int post_id = arr.getJSONObject(i).getInt("id");
            int i = new Random().nextInt(arr.length());

            MarsRoverPhotos mrp_Temp = new MarsRoverPhotos();
            mrp_Temp.setId(arr.getJSONObject(i).getInt("id"));
            //mrp_Temp.setSol((String) arr.getJSONObject(i).getInt("sol"));
            mrp_Temp.setSol(Integer.toString(arr.getJSONObject(i).getInt("sol")));
            mrp_Temp.setImg_src(arr.getJSONObject(i).getString("img_src"));
            mrp_Temp.setEarth_date(arr.getJSONObject(i).getString("earth_date"));
            mrp_Temp.setMarsRoverCamera(new MarsRoverCamera(arr.getJSONObject(i).getJSONObject("camera").getInt("id"),
                    arr.getJSONObject(i).getJSONObject("camera").getString("name"), arr.getJSONObject(i).getJSONObject("camera").getString("full_name")));
            mrp_Temp.setMarsRoverInfo(new MarsRoverInfo(arr.getJSONObject(i).getJSONObject("rover").getInt("id"),
                    arr.getJSONObject(i).getJSONObject("rover").getString("name"),
                    arr.getJSONObject(i).getJSONObject("rover").getString("landing_date"),
                    arr.getJSONObject(i).getJSONObject("rover").getString("launch_date"),
                    arr.getJSONObject(i).getJSONObject("rover").getString("status")));

            marsRoverPhotos.add(mrp_Temp);


        }
        log.info("RequestID: {} - MARS_ROVER REQUEST - Response - {}", requestID, Arrays.toString(marsRoverPhotos.toArray()));
        mav.addObject("marsRoverPhotos",marsRoverPhotos);

        return mav;
    }




}