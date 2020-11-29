package com.project.microservices.pagamento.service;

import com.project.microservices.pagamento.model.ProdutoVenda;
import com.project.microservices.pagamento.repository.ProdutoVendaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoVendaService {

  private final ProdutoVendaRepository produtoVendaRepository;

  public ProdutoVenda create(ProdutoVenda produtoVenda) {
    return produtoVendaRepository.save(produtoVenda);
  }
}
