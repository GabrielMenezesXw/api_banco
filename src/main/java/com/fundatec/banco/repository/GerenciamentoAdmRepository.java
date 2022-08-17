package com.fundatec.banco.repository;

import com.fundatec.banco.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GerenciamentoAdmRepository extends JpaRepository<Banco, Integer> {
}
