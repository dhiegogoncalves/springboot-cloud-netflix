package com.project.microservices.crud.service;

import com.project.microservices.crud.exception.ResourceNotFoundException;
import com.project.microservices.crud.message.ProdutoMessage;
import com.project.microservices.crud.model.Produto;
import com.project.microservices.crud.repository.ProdutoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

  private final ProdutoRepository produtoRepository;
  private final ProdutoMessage produtoMessage;

  public Page<Produto> findAll(Pageable pageable) {
    return produtoRepository.findAll(pageable);
  }

  public Produto findById(Long id) {
    return produtoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("O produto não foi encontrado"));
  }

  public Produto create(Produto produto) {
    produto = produtoRepository.save(produto);
    produtoMessage.sendMessage(produto);
    return produto;
  }

  public Produto update(Produto produto) {
    Produto produtoEncontrado = findById(produto.getId());

    produtoEncontrado.setNome(produto.getNome());
    produtoEncontrado.setEstoque(produto.getEstoque());
    produtoEncontrado.setPreco(produto.getPreco());

    return produtoRepository.save(produtoEncontrado);
  }

  public void delete(Long id) {
    Produto produto = findById(id);
    produtoRepository.delete(produto);
  }
}
