package com.vssv.chatsapp.entity;


import com.fasterxml.jackson.core.type.TypeReference;
import com.vssv.chatsapp.utils.BaseUtils;
import com.vssv.chatsapp.utils.JsonUtils;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TokenEntity {

    private String userId;
    private List<String> roles;

    @Override
    public String toString(){
        return BaseUtils.toJsonString(this);
    }

    public static TokenEntity getToken(String jsonString) throws IOException {
        return JsonUtils.getInstance().toObject(jsonString, new TypeReference<TokenEntity>(){});
    }
}
