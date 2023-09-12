package com.btk.rabbitmq.producer;

import com.btk.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMailProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.registerMailBindingKey}")
    private String registerMailBindingKey;
    public void sendActivationCode(RegisterMailModel registerMailModel) {
        rabbitTemplate.convertAndSend(directExchange,registerMailBindingKey,registerMailModel);
    }
}
