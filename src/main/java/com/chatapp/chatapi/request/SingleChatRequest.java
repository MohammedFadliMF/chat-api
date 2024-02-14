package com.chatapp.chatapi.request;

public class SingleChatRequest {
    private Long userId;
    public SingleChatRequest(){

    }
    
    public SingleChatRequest(Long userId) {
        super();
        this.userId=userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
