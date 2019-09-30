package com.lostpet.backend.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableStompBrokerRelay("/topic")
//                .setRelayHost("localhost")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest");
        config.enableSimpleBroker("/user_chat");
        config.setApplicationDestinationPrefixes("/lost_pet_chat");
        config.setUserDestinationPrefix("/user_notifications");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-websocket").setAllowedOrigins("*").withSockJS();
    }



}