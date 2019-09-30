package com.lostpet.backend.chat.controller;

import com.lostpet.backend.chat.model.ChatMessage;
import com.lostpet.backend.chat.model.Greeting;
import com.lostpet.backend.chat.model.HelloMessage;
import com.lostpet.backend.service.ChatCommentService;
import com.lostpet.backend.service.ChatService;
import com.lostpet.backend.service.impl.NotificationService;
import com.tudor.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class ChatController {

    private ChatService chatService;
    private ChatCommentService chatCommentService;
    private NotificationService notificationService;

    @Autowired
    public ChatController(ChatService chatService, ChatCommentService chatCommentService, NotificationService notificationService) {
        this.chatService = chatService;
        this.chatCommentService = chatCommentService;
        this.notificationService = notificationService;
    }


    @MessageMapping("/user_chat")
    @SendTo("/user_chat/")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    @MessageMapping("/user_chat/{chatName}")
    @SendTo("/user_chat/{chatName}")
    public ChatMessage greeting( @Payload ChatMessage message, @DestinationVariable String chatName) throws Exception {
        //Thread.sleep(1000); // simulated delay
        //return new ChatMessage( HtmlUtils.htmlEscape(message.get()));
        chatCommentService.save(message);

        //String username = principal.getName();
       notificationService.notify(new Notification("newMessage"),message.getSenderUsername());

//        notifyUser(new Notification("newMessage"),message.getSenderUsername());
        return message;
    }

//    @MessageMapping("/user_chat/{chatName}")
//    @SendTo("/chat_notifications/as")
//    public ChatMessage notifyUser(@Payload ChatMessage message,@DestinationVariable String username) throws Exception {
//        //Thread.sleep(1000); // simulated delay
//        //return new ChatMessage( HtmlUtils.htmlEscape(message.get()))
//        return message;
//    }

    @MessageMapping("/chat.openChat")
    @SendTo("/user_chat/chat/")
    public ChatMessage openChat(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        String chatName = chatMessage.getSenderUsername()+chatMessage.getDest();
        headerAccessor.getSessionAttributes().put("chatName", chatName);

        return chatMessage;
    }

}