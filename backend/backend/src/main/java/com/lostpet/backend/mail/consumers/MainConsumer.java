package com.lostpet.backend.mail.consumers;

import com.lostpet.backend.mail.services.MailService;
import com.lostpet.backend.mail.services.WriteToFileService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.tudor.dto.MailDTO;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainConsumer extends DefaultConsumer {

    private static MailService mailService;
    private static WriteToFileService writeToFileService;

    public MainConsumer(Channel channel) {
        super(channel);

        mailService = new MailService("tudordummymail@gmail.com","thisisnotarealpassword");
        //writeToFileService = new WriteToFileService();
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        super.handleDelivery(consumerTag, envelope, properties, body);
        //String message = new String(body, "UTF-8");
        MailDTO mailDTO = new MailDTO();
        try {
            mailDTO = (MailDTO) deserialize(body);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        mailService.sendMail(mailDTO.getDest(),mailDTO.getSubject(),mailDTO.getMessage());
        //writeToFileService.writeToFile(message);

        //System.out.println(" [x] Received '" + message + "'");
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
