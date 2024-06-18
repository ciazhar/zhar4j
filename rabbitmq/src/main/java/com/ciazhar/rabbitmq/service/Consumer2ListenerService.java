package com.ciazhar.rabbitmq.service;

import com.ciazhar.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer2ListenerService {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_2)
    public void receiveMessageFromQueue2(String message) {
        System.out.println("Consumer2 received from Queue2: " + message);
    }
}
