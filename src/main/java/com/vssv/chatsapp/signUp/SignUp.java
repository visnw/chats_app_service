package com.vssv.chatsapp.signUp;

import com.vssv.chatsapp.BaseUtils;
import com.vssv.chatsapp.dao.DynamoDao;
import org.json.simple.JSONObject;

public class SignUp {

    public JSONObject registerUser(JSONObject paramMap){
        DynamoDao.getInstance().addData("user_details", "user_id", paramMap);
        return BaseUtils.getResponseJson(true, "User registered");
    }

}
