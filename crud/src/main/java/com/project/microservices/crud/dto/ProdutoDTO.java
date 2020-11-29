package com.project.microservices.crud.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

  private Long id;

  @NotNull
  @NotEmpty(message = "O nome do produto não pode estar em branco")
  private String nome;

  @NotNull(message = "A quantidade de estoque do produto é obrigatório.")
  private Integer estoque;

  @NotNull(message = "O preço do produto é obrigatório")
  private Double preco;
}
