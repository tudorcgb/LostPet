package com.lostpet.backend.service.impl;

import com.tudor.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertFalse;


@Service
public class NotificationService {

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        //messagingTemplate.setUserDestinationPrefix("/chat_notifications/");
    }

    /**
     * Send notification to users subscribed on channel "/chat_notifications/username".
     *
     * The message will be sent only to the user with the given username.
     *
     * @param notification The notification message.
     * @param username The username for the user to send notification.
     */
    public void notify(Notification notification, String username) {

        messagingTemplate.convertAndSend("/queue/greeting-as",notification);
        //messagingTemplate.convertAndSendToUser( username,"/user_chat", notification);

        return;
    }
}
