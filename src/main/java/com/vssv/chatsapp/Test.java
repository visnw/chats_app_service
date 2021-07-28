package com.vssv.chatsapp;

import com.vssv.chatsapp.dao.DynamoDao;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse("{\"tableName\":\"user_details\",\"primary\":\"user_id\",\"tableData\":{\"user_id\":\"9597451135\",\"first_name\":\"Vishnu\",\"last_name\":\"Vardhan\",\"status\":\"available on chat\"}}");
            String tableName = String.valueOf(json.get("tableName"));
            String primary = String.valueOf(json.get("primary"));
            JSONObject data = (JSONObject) json.get("tableData");
            System.out.println(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
