package com.project.microservices.pagamento.message;

import com.project.microservices.pagamento.dto.ProdutoDTO;
import com.project.microservices.pagamento.model.Produto;
import com.project.microservices.pagamento.service.ProdutoService;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoMessage {

  private final ProdutoService produtoService;
  private final ModelMapper modelMapper;

  @RabbitListener(queues = { "${crud.rabbitmq.routing-key}" })
  public void receive(@Payload ProdutoDTO produtoDTO) {
    Produto produto = modelMapper.map(produtoDTO, Produto.class);
    produtoService.save(produto);
  }
}
