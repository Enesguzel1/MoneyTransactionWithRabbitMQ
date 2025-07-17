package org.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${eg-bank-rabbit-queue1-name}")
    private String queue1Name;
    @Value("${eg-bank-rabbit-queue2-name}")
    private String queue2Name;
    @Value("${eg-bank-rabbit-queue3-name}")
    private String queue3Name;
    @Value("${eg-bank-rabbit-exchange-name}")
    private String exchangeName;
    @Value("${eg-bank-rabbit-routing-name}")
    private String routingName;
    @Bean
    public Queue firstStepQueue() {
        return new Queue(queue1Name);
    }
    @Bean
    public Queue secondStepQueue(){
        return new Queue(queue2Name);
    }
    @Bean
    public Queue thirdStepQueue(){
        return new Queue(queue3Name);
    }
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Binding binding(Queue firstStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(firstStepQueue).to(exchange).with(routingName);
    }
    @Bean
    public Binding secondBinding(Queue secondStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(secondStepQueue).to(exchange).with("secondRoute");
    }
    @Bean
    public Binding thirdBinding(Queue thirdStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(thirdStepQueue).to(exchange).with("thirdRoute");
    }
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
