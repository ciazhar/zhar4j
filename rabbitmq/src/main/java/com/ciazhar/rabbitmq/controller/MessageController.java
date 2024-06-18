package com.ciazhar.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.ciazhar.rabbitmq.config.RabbitMQConfig.*;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/sendToQueue1")
    public String sendMessageToQueue1(@RequestParam String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_1, message);
        return "Message sent to Queue1: " + message;
    }

    @GetMapping("/sendToQueue2")
    public String sendMessageToQueue2(@RequestParam String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_2, message);
        return "Message sent to Queue2: " + message;
    }
}