package com.project.microservices.crud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Autowired
  private ConnectionFactory connectionFactory;

  @Value("${crud.rabbitmq.routing-key}")
  private String queue;

  @Value("${crud.rabbitmq.exchange}")
  private String exchange;

  @Value("${crud.rabbitmq.dead-letter}")
  private String deadLetter;

  @Bean
  public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter());
    return factory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Queue queue() {
    return QueueBuilder.durable(queue).deadLetterExchange(exchange).deadLetterRoutingKey(deadLetter).build();
  }

  @Bean
  Queue deadLetter() {
    return QueueBuilder.durable(deadLetter).deadLetterExchange(exchange).deadLetterRoutingKey(queue).build();
  }

  @Bean
  public Binding bindingQueue() {
    return BindingBuilder.bind(queue()).to(exchange()).with(queue);
  }

  @Bean
  public Binding bindingDeadLetter() {
    return BindingBuilder.bind(deadLetter()).to(exchange()).with(deadLetter);
  }

}
