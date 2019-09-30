package com.lostpet.backend.mail.connection;

//import org.apache.commons.lang.SerializationUtils;
import com.lostpet.backend.mail.consumers.MainConsumer;
import com.rabbitmq.client.Consumer;
import com.tudor.dto.CommentDTO;
import com.tudor.dto.MailDTO;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class ServerConnection extends AbstractServerConnection {

    private static ServerConnection connection;

    private ServerConnection() throws IOException, TimeoutException {

        Consumer consumer = new MainConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        System.out.println(" Waiting for messages. To exit press CTRL+C");
    }
    private ServerConnection(String queue) throws IOException, TimeoutException {

        Consumer consumer = new MainConsumer(channel);
        channel.basicConsume(queue, true, consumer);

        System.out.println(" Waiting for messages. To exit press CTRL+C");
    }

    public void sendMessage(Serializable object) throws IOException {
            channel.basicPublish("",QUEUE_NAME, null, SerializationUtils.serialize(object));
    }
    public void sendMessage(CommentDTO object) throws IOException {

        channel.basicPublish("",QUEUE_NAME, null, SerializationUtils.serialize(object));
    }
    public void sendMessage(String object) throws IOException {
        channel.basicPublish("",QUEUE_NAME, null, object.getBytes());
    }
    public static ServerConnection getInstance() throws IOException, TimeoutException {
        if(connection == null){
            connection = new ServerConnection();
        }
        return connection;
    }
    public static ServerConnection getInstance(String queue) throws IOException, TimeoutException {
        if(connection == null){
            connection = new ServerConnection(queue);
        }
        return connection;
    }

    public void sendMessage(MailDTO mailDTO) throws IOException{
        channel.basicPublish("",QUEUE_NAME, null, SerializationUtils.serialize(mailDTO));
    }
}
