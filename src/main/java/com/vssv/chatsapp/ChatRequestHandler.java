package com.vssv.chatsapp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.vssv.chatsapp.dao.DynamoDao;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class ChatRequestHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse>{
    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context)
    {
        APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
        Map<String, String> params = event.getQueryStringParameters();
        response.setBody("Hi for lambda response *****$$#$#" + " :: " + event
                + params);
        response.setStatusCode(200);

        String messageBody = event.getBody();
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(messageBody);
            String tableName = String.valueOf(json.get("tableName"));
            String primary = String.valueOf(json.get("primary"));
            JSONObject data = (JSONObject) json.get("tableData");
            DynamoDao dynamo = new DynamoDao();
            dynamo.addData(tableName, primary, data);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return response;
    }
}