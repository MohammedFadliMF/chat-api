package com.chatapp.chatapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.chatapi.entities.Message;
import com.chatapp.chatapi.entities.User;
// import com.chatapp.chatapi.exceptions.ChatException;
import com.chatapp.chatapi.exceptions.MessageException;
import com.chatapp.chatapi.exceptions.UserException;
import com.chatapp.chatapi.request.SendMessageRequest;
import com.chatapp.chatapi.response.ApiResponse;
import com.chatapp.chatapi.services.MessageService;
import com.chatapp.chatapi.services.UserService;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/messages")
public class MessageController {

    // @Autowired
    // private MessageService messageService;
    // @Autowired
    // private UserService userService;

    // @PostMapping("/create")
    // public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ChatException{
    //     User user=userService.findUserProfile(jwt);

    //     req.setUserId(user.getId());
    //     Message message=messageService.sendMessage(req);
    //     return new ResponseEntity<>(message,HttpStatus.OK);
    // }
    
    // @GetMapping("/chat/{chatId}")
    // public ResponseEntity<List <Message>> getChatMessageHandler(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
    //     User user = userService.findUserProfile(jwt);

    //     List< Message> messages = messageService.getChatMessages(chatId, user);
    //     return new ResponseEntity<>(messages, HttpStatus.OK);
    // }

    // @DeleteMapping("/{messageId}")
    // public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Long messageId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException, MessageException {
    //     User user = userService.findUserProfile(jwt);

    //     messageService.deleteMessage(messageId, user);
    //     ApiResponse res=new ApiResponse("message deleted successfully", false);
    //     return new ResponseEntity<>(res, HttpStatus.OK);
    // }
}
