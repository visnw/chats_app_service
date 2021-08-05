package com.vssv.chatsapp.service.auth;

import com.vssv.chatsapp.dao.DynamoDao;
import com.vssv.chatsapp.entity.UserCredEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CredentialValidator {
    UserCredEntity userEntity;
    public CredentialValidator(UserCredEntity userEntity){
        this.userEntity = userEntity;
    }

    public boolean validate(){
        List<Map<String, Object>> userDetails = DynamoDao.getInstance().getData("user_details", userEntity.getUserId());
        if(!userDetails.isEmpty()){
            Map<String, Object> userDetail = userDetails.get(0);
            if(Objects.nonNull(userDetail.get("password"))){
                String actualPassword = String.valueOf(userDetail.get("password"));
                return validatePassword(actualPassword, userEntity.getPassword());
            }
        }
        return false;
    }

    public boolean validatePassword(String password, String inputPassword){
        return password.equals(inputPassword);
    }

}
