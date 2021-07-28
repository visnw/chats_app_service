package com.vssv.chatsapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BaseUtils {

    public static JSONObject getJsonObject(Object object){
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(String.valueOf(object));
        } catch (ParseException e) {
            return new JSONObject();
        }
    }

    public static JSONObject getResponseJson(Object status, Object message){
        JSONObject response = new JSONObject();
        response.put("status", status);
        response.put("message", message);
        return response;
    }
}
