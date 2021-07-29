package com.vssv.chatsapp.service;

import com.vssv.chatsapp.utils.BaseUtils;
import com.vssv.chatsapp.dao.DynamoDao;

import java.util.Map;

public class MessageService extends BaseUtils {

    public Map<String, Object> getMessage(Map<String, Object> paramMap){
        String chatId = getString(paramMap, "chat_id");
        return makeResponse(true,
                DynamoDao.getInstance().getData("user_details", chatId));
    }

    public static Map<String, Object> processRequest(Map<String, Object> paramMap) {
        MessageService messageService = new MessageService();
        return messageService.getMessage(paramMap);
    }
}
