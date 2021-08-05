package com.vssv.chatsapp.service.chatService;

import com.vssv.chatsapp.dao.DynamoDao;
import com.vssv.chatsapp.utils.BaseUtils;

import java.util.Map;

public class ChatPage extends BaseUtils {

    public Map<String, Object> getPageDetails(Map<String, Object> paramMap){
        String chatId = getString(paramMap, "chat_id");
        return makeResponse(true,
                DynamoDao.getInstance().getData("user_details", chatId));
    }

    public static Map<String, Object> processRequest(Map<String, Object> paramMap) {
        ChatPage chatPage = new ChatPage();
        return chatPage.getPageDetails(paramMap);
    }

}
