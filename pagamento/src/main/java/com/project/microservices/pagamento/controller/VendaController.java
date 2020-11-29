package com.project.microservices.pagamento.controller;

import javax.validation.Valid;

import com.project.microservices.pagamento.dto.VendaDTO;
import com.project.microservices.pagamento.model.Venda;
import com.project.microservices.pagamento.service.VendaService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

  private final VendaService vendaService;
  private final ModelMapper modelMapper;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<VendaDTO> listAll(Pageable pageable) {
    return vendaService.findAll(pageable).map(venda -> modelMapper.map(venda, VendaDTO.class));
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public VendaDTO findById(@PathVariable Long id) {
    Venda venda = vendaService.findById(id);
    return modelMapper.map(venda, VendaDTO.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VendaDTO create(@Valid @RequestBody VendaDTO vendaDTO) {
    Venda venda = modelMapper.map(vendaDTO, Venda.class);
    venda = vendaService.create(venda);
    return modelMapper.map(venda, VendaDTO.class);
  }
}
