package com.project.microservices.pagamento.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venda")
public class Venda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @DateTimeFormat(pattern = "MM/dd/yyy")
  @Column(nullable = false)
  private Date data;

  @OneToMany(mappedBy = "venda", cascade = { CascadeType.PERSIST })
  private List<ProdutoVenda> produtos;

  @Column(name = "valor_total", nullable = false, length = 10)
  private Double valorTotal;
}
