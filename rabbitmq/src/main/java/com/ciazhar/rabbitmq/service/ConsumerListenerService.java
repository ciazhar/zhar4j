package com.ciazhar.rabbitmq.service;

import com.ciazhar.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerListenerService {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_1)
    public void receiveMessageFromQueue1(String message) {
        System.out.println("Consumer1 received from Queue1: " + message);
    }
}
