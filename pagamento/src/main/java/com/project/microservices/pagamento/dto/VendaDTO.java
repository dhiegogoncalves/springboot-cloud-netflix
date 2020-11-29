package com.project.microservices.pagamento.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

  private Long id;
  private Date data;
  private List<ProdutoVendaDTO> produtos;
  private Double valorTotal;
}
