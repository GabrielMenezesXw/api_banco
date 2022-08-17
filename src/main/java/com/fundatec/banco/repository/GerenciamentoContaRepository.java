package com.fundatec.banco.repository;

import com.fundatec.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface
GerenciamentoContaRepository extends JpaRepository<Conta, Integer>{

    Optional<Conta> findByCpfTitular(String cpfTitular);

}
