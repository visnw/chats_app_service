package com.vssv.chatsapp;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.vssv.chatsapp.service.MessageService;
import com.vssv.chatsapp.service.SignUp;
import com.vssv.chatsapp.utils.BaseUtils;

import java.util.Map;

public class RequestNavigator {

    private static final String BASE_PATH = "/chatsapp";
    private APIGatewayProxyRequestEvent event;
    private String path;
    private Map<String, String> requestParam;
    private Map<String, Object> paramMap;

    public static void processRequest(APIGatewayProxyRequestEvent event, APIGatewayProxyResponseEvent response){
        RequestNavigator instance = new RequestNavigator();
        instance.event = event;
        instance.path = event.getPath();
        instance.requestParam = event.getQueryStringParameters();
        instance.paramMap = BaseUtils.convertJsonToMap(event.getBody());
        response.setBody(BaseUtils.toJsonString(instance.doProcessRequest()));
    }

    private Map<String, Object> doProcessRequest(){
        String methodName = path.replaceFirst(BASE_PATH, "");
        switch (methodName) {
            case "/login" :
                return BaseUtils.makeResponse(true, "Login not supported");
            case "/signUp" :
                return SignUp.processRequest(paramMap);
            case "/getMessage" :
                return MessageService.processRequest(paramMap);
            default:
                return BaseUtils.makeResponse(false, "path not supported");
        }
    }







}
