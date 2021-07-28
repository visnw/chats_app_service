package com.vssv.chatsapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Message {
    String messageId;
    String message;
    boolean isSend;
}
