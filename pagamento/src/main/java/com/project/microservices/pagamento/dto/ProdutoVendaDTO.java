package com.project.microservices.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendaDTO {

  private Long id;
  private Long produtoId;
  private Integer quantidade;
}
