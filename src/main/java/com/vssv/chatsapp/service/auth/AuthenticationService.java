package com.vssv.chatsapp.service.auth;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.vssv.chatsapp.entity.TokenEntity;
import com.vssv.chatsapp.entity.UserCredEntity;

import javax.ws.rs.core.HttpHeaders;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationService {

    private String SECRET_KEY;

    public AuthenticationService(){
        SECRET_KEY = "suryaTheDon";
    }

    public Map<String, Object> authenticate (UserCredEntity userEntity, APIGatewayProxyResponseEvent response) {

        Map<String, Object> result = new HashMap<>();
        CredentialValidator credValidator = new CredentialValidator(userEntity);

        if(credValidator.validate()){
            Map<String, String> headerMap = new HashMap<>();
            JwtProcessor tokenProcessor = new JwtProcessor("suryaTheDon");
            TokenEntity token = new TokenEntity();
            token.setUserId(userEntity.getUserId());
            token.setRoles(Arrays.asList("chat_user"));
            String jwtToken = tokenProcessor.generateJWT(UUID.randomUUID().toString(), token.toString(), -1);
            System.out.println(jwtToken);
            headerMap.put(HttpHeaders.SET_COOKIE, jwtToken);
            response.setHeaders(headerMap);
            result.put("isAuthenticated", true);
            System.out.println("login process -- success cookie set");
        } else {
            System.out.println("login process -- invalidCredentials");
            result.put("isAuthenticated", false);
            result.put("invalidCredentials", true);
        }
        return result;
    }



}
