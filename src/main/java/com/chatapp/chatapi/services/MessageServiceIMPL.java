package com.chatapp.chatapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

// import com.chatapp.chatapi.entities.Chat;
import com.chatapp.chatapi.entities.Message;
import com.chatapp.chatapi.entities.User;
// import com.chatapp.chatapi.exceptions.ChatException;
import com.chatapp.chatapi.exceptions.MessageException;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.repositories.MessageRepository;
import com.chatapp.chatapi.request.SendMessageRequest;

public class MessageServiceIMPL implements MessageService{


    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    // @Autowired                                                                                                          
    // private ChatService chatService;

    // @Override
    // public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {

    //     User user=userService.findUserById(req.getUserId());
    //     Chat chat=chatService.findChatById(req.getChatId());

    //     Message message=new Message();
    //     message.setChat(chat);
    //     message.setUser(user);
    //     message.setContent(req.getContent());
    //     message.setTimestamp(LocalDateTime.now());
        
    //     return message;
    // }

    // @Override
    // public List<Message> getChatMessages(Integer chatId,User reqUser) throws ChatException, UserException {
       
    //     Chat chat = chatService.findChatById(chatId);
    //     if (!chat.getUsers().contains(reqUser)) {
    //         throw new UserException("you are not releted to this chat "+chat.getId());
    //     }
    //     List<Message> messages=messageRepository.findByChatId(chat.getId());
        
    //     return messages;
    // }

    // @Override
    // public Message findMessageById(Long messageId) throws MessageException {

    //     Optional<Message>opt=messageRepository.findById(messageId);
    //     if (opt.isPresent()) {
    //         return opt.get();
    //     }
    //     throw new MessageException("message not found with id : "+messageId);
    // }

    // @Override
    // public void deleteMessage(Long messageId, User reqUser) throws MessageException, UserException {

    //     Message message=findMessageById(messageId);

    //     if (message.getUser().getId().equals(reqUser.getId())) {
    //         messageRepository.deleteById(messageId);
    //     }
    //     throw new UserException("you cant delete another message");
    // }
    
}
