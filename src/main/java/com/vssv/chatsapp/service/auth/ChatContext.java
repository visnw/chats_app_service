package com.vssv.chatsapp.service.auth;

import com.vssv.chatsapp.entity.TokenEntity;
import lombok.Data;

@Data
public class ChatContext {
    private static String userId;

    public static void setContext(TokenEntity entity){
        userId = entity.getUserId();
    }

}
