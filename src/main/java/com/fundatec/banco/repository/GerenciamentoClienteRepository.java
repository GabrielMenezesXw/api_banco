package com.fundatec.banco.repository;

import com.fundatec.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GerenciamentoClienteRepository extends JpaRepository<Cliente, Integer> {
}
