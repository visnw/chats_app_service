package com.vssv.chatsapp.service.chatService;

import com.vssv.chatsapp.utils.BaseUtils;
import com.vssv.chatsapp.dao.DynamoDao;

import java.util.Map;

public class SignUp extends BaseUtils {

    public Map<String, Object>  registerUser(Map<String, Object> paramMap){
        DynamoDao.getInstance().addData("user_details", "user_id", paramMap);
        return makeResponse(true, "User registered");
    }

    public static Map<String, Object> processRequest(Map<String, Object> paramMap) {
        SignUp signUp = new SignUp();
        return signUp.registerUser(paramMap);
    }
}
