package com.project.microservices.crud.message;

import com.project.microservices.crud.dto.ProdutoMessageDTO;
import com.project.microservices.crud.model.Produto;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoMessage {

  @Value("${crud.rabbitmq.routing-key}")
  private String queue;

  @Value("${crud.rabbitmq.exchange}")
  private String exchange;

  private final RabbitTemplate rabbitTemplate;
  private final ModelMapper modelMapper;

  public void sendMessage(Produto produto) {
    ProdutoMessageDTO produtoMessageDTO = modelMapper.map(produto, ProdutoMessageDTO.class);

    try {
      rabbitTemplate.convertAndSend(exchange, queue, produtoMessageDTO);
    } catch (Exception ex) {
      throw new AmqpRejectAndDontRequeueException(ex);
    }
  }
}
