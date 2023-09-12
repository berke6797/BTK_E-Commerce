package com.btk.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-auth}")
    String exchange;

    //Mail işlemi için ->
    @Value("${rabbitmq.registerMailQueue}")
    private String registerMailQueue;

    @Value("${rabbitmq.registerMailBindingKey}")
    private String registerMailBindingKey;

    @Bean
    DirectExchange directAuthExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue registerMailQueue() {
        return new Queue(registerMailQueue);
    }

    @Bean
    public Binding bindingRegisterMail(final Queue registerMailQueue, final DirectExchange directAuthExchange) {
        return BindingBuilder.bind(registerMailQueue).to(directAuthExchange).with(registerMailBindingKey);
    }

}
