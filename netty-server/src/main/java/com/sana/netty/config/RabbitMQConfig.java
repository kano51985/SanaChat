package com.sana.netty.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public TopicExchange chatExchange() {
        return new TopicExchange("chat-exchange");
    }

    @Bean
    public Queue privateQueue() {
        return new Queue("private-queue");
    }

    @Bean
    public Binding privateBinding(Queue privateQueue,TopicExchange chatExchange) {
        return BindingBuilder.bind(privateQueue).to(chatExchange).with("private.user.*");
    }
}
