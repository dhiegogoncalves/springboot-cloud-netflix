package com.project.microservices.crud.controller;

import javax.validation.Valid;

import com.project.microservices.crud.dto.ProdutoDTO;
import com.project.microservices.crud.model.Produto;
import com.project.microservices.crud.service.ProdutoService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

  private final ProdutoService produtoService;
  private final ModelMapper modelMapper;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<ProdutoDTO> listAll(Pageable pageable) {
    return produtoService.findAll(pageable).map(produto -> modelMapper.map(produto, ProdutoDTO.class));
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProdutoDTO findById(@PathVariable Long id) {
    Produto produto = produtoService.findById(id);
    return modelMapper.map(produto, ProdutoDTO.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProdutoDTO create(@Valid @RequestBody ProdutoDTO productDTO) {
    Produto produto = modelMapper.map(productDTO, Produto.class);
    produto = produtoService.create(produto);
    return modelMapper.map(produto, ProdutoDTO.class);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public ProdutoDTO update(@Valid @RequestBody ProdutoDTO productDTO) {
    Produto produto = modelMapper.map(productDTO, Produto.class);
    produto = produtoService.update(produto);
    return modelMapper.map(produto, ProdutoDTO.class);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    produtoService.delete(id);
  }
}
