package com.chatapp.chatapi.request;

public class SendMessageRequest {
    private Long userId;
    private Integer chatId;
    private String content;

    public SendMessageRequest() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getChatId() {
        return this.chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
