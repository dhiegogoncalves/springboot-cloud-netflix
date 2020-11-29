package com.project.microservices.pagamento.service;

import com.project.microservices.pagamento.exception.ResourceNotFoundException;
import com.project.microservices.pagamento.model.Venda;
import com.project.microservices.pagamento.repository.VendaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendaService {

  private final VendaRepository vendaRepository;

  public Page<Venda> findAll(Pageable pageable) {
    return vendaRepository.findAll(pageable);
  }

  public Venda findById(Long id) {
    return vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("A venda não foi encontrado"));
  }

  @Transactional
  public Venda create(Venda venda) {
    return vendaRepository.save(venda);
  }
}
