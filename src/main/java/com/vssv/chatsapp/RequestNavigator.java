package com.vssv.chatsapp;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.vssv.chatsapp.signUp.SignUp;
import org.json.simple.JSONObject;

import java.util.Map;

public class RequestNavigator {

    private static final String BASE_PATH = "/chatsapp";
    private APIGatewayProxyRequestEvent event;
    private String path;
    private JSONObject messageBody;
    private Map<String, String> params;

    public static void processRequest(APIGatewayProxyRequestEvent event, APIGatewayProxyResponseEvent response){
        RequestNavigator instance = new RequestNavigator();
        instance.event = event;
        instance.path = event.getPath();
        instance.params = event.getQueryStringParameters();
        instance.messageBody = BaseUtils.getJsonObject(event.getBody());
        response.setBody(instance.doProcessRequest().toString());
    }

    private JSONObject doProcessRequest(){
        String methodName = path.replaceFirst(BASE_PATH, "");
        switch (methodName) {
            case "/login" :
                return BaseUtils.getResponseJson(true, "Login not supported");
            case "/signUp" :
                SignUp signUp = new SignUp();
                return signUp.registerUser(messageBody);
            default:
                return BaseUtils.getResponseJson(false, "path not supported");
        }
    }







}
