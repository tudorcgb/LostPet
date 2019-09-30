package com.lostpet.backend.mail.connection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public abstract class AbstractServerConnection {


    public static final String QUEUE_NAME = "comment_queue";
    public static final String HOST_NAME = "localhost";


    private static Connection connection;
    protected Channel channel;

    protected AbstractServerConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        //System.out.println(" Waiting for messages. To exit press CTRL+C");
    }

//    public static AbstractServerConnection getInstance() throws IOException, TimeoutException {
//        if(connection == null){
//            connection = new AbstractServerConnection();
//        }
//        return connection;
//    }
}
