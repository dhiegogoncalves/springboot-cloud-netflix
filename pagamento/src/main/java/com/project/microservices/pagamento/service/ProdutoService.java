package com.project.microservices.pagamento.service;

import com.project.microservices.pagamento.model.Produto;
import com.project.microservices.pagamento.repository.ProdutoRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

  private final ProdutoRepository produtoRepository;

  public Produto save(Produto produto) {
    return produtoRepository.save(produto);
  }
}
