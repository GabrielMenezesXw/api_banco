package com.fundatec.banco.repository;

import com.fundatec.banco.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
}
