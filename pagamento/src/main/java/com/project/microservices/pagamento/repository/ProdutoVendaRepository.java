package com.project.microservices.pagamento.repository;

import com.project.microservices.pagamento.model.ProdutoVenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

}
