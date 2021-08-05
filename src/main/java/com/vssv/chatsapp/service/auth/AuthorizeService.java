package com.vssv.chatsapp.service.auth;

import com.vssv.chatsapp.entity.TokenEntity;

import javax.ws.rs.core.HttpHeaders;
import java.util.Map;
import java.util.Objects;

public class AuthorizeService {

    public static boolean validateAuth(Map<String, String> header) {
        if(Objects.nonNull(header) && header.containsKey(HttpHeaders.COOKIE.toLowerCase())){
            System.out.println("validating -- has cookie");
            String jwtToken = header.get(HttpHeaders.COOKIE.toLowerCase());
            JwtProcessor processor = new JwtProcessor("suryaTheDon");
            TokenEntity dat = processor.getTokenData(jwtToken);
            System.out.println("token entity : " + dat);
            if(Objects.nonNull(dat)){
                ChatContext.setContext(dat);
                return true;
            }
        } else {
            System.out.println("validating -- no cookie in header");
        }
        return false;
    }

}
