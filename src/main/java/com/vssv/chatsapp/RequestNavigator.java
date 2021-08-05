package com.vssv.chatsapp;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.vssv.chatsapp.service.chatService.ChatPage;
import com.vssv.chatsapp.service.chatService.Login;
import com.vssv.chatsapp.service.chatService.MessageService;
import com.vssv.chatsapp.service.chatService.SignUp;
import com.vssv.chatsapp.service.auth.AuthorizeService;
import com.vssv.chatsapp.utils.BaseUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestNavigator {

    private static final String BASE_PATH = "/chatsapp";
    private APIGatewayProxyRequestEvent event;
    private APIGatewayProxyResponseEvent response;
    private String path;
    private Map<String, String> requestParam;
    private Map<String, Object> paramMap;

    public static void processRequest(APIGatewayProxyRequestEvent event, APIGatewayProxyResponseEvent response){
        RequestNavigator instance = new RequestNavigator();
        instance.event = event;
        instance.path = event.getPath();
        instance.requestParam = event.getQueryStringParameters();
        instance.paramMap = BaseUtils.convertJsonToMap(event.getBody());
        instance.response = response;
        instance.doProcessRequest();
    }

    private void doProcessRequest() {
        Map<String, Object> result = new HashMap<>();
        String methodName = path.replaceFirst(BASE_PATH, "");
        System.out.println("methodName : " +  methodName);

        boolean isLogin = methodName.equals("/login");
        boolean validAuth = validateAuth();
        if(methodName.equals("/signUp")){
            result.putAll(SignUp.processRequest(paramMap));
        } else if(isLogin){
            if(validAuth){
                result.putAll(BaseUtils.makeResponse(true, "already_logged_in"));
            } else {
                result.putAll(Login.processRequest(paramMap, response));
            }
        } else if(validAuth){
            switch (methodName) {
                case "/getMessage" :
                    result.putAll(MessageService.processRequest(paramMap));
                    break;
                case "/chats" :
                    result.putAll(ChatPage.processRequest(paramMap));
                    break;
                default :
                    result.putAll(BaseUtils.makeResponse(false, "path not supported"));
            }
        } else {
            result.putAll(BaseUtils.makeResponse(false, "need_to_login"));
        }
        response.setBody(BaseUtils.toJsonString(result));
    }

    // check if it contains cookie
    private boolean validateAuth() {
        System.out.println("validating request .. ");
        Map<String, String> headerMap = event.getHeaders();
        return AuthorizeService.validateAuth(headerMap);
    }


}
