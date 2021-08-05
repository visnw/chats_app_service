package com.vssv.chatsapp.service.chatService;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.vssv.chatsapp.entity.UserCredEntity;
import com.vssv.chatsapp.service.auth.AuthenticationService;

import java.util.Map;

public class Login {
    public Map<String, Object> processLogin(Map<String, Object> paramMap, APIGatewayProxyResponseEvent response){
        UserCredEntity userEntity = new UserCredEntity();
        userEntity.setUserId(String.valueOf(paramMap.get("user_id")));
        userEntity.setPassword(String.valueOf(paramMap.get("password")));
        System.out.println("login process -- started " + userEntity);
        AuthenticationService auth = new AuthenticationService();
        return auth.authenticate(userEntity, response);
    }

    public static Map<String, Object> processRequest(Map<String, Object> paramMap, APIGatewayProxyResponseEvent response) {
        Login login = new Login();
        return login.processLogin(paramMap, response);
    }
}
