package com.chatapp.chatapi.request;

import java.util.List;

public class GroupChatRequest {
    private List<Long> userIds;
    private String chat_name;
    private String chat_image;

    public GroupChatRequest() {
    }

    public GroupChatRequest(List<Long> userIds, String chat_name, String chat_image) {
        this.userIds = userIds;
        this.chat_name = chat_name;
        this.chat_image = chat_image;
    }

    public List<Long> getUserIds() {
        return this.userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getChat_name() {
        return this.chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getChat_image() {
        return this.chat_image;
    }

    public void setChat_image(String chat_image) {
        this.chat_image = chat_image;
    }

}
