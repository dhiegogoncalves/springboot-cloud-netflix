package com.project.microservices.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoMessageDTO {

  private Long id;
  private Integer estoque;
}
