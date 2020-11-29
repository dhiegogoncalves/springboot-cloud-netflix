package com.project.microservices.pagamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto_venda")
public class ProdutoVenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "produto_id", nullable = false, length = 10)
  private Long produtoId;

  @Column(nullable = false, length = 10)
  private Integer quantidade;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venda_id")
  private Venda venda;
}
