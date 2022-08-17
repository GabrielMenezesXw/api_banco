package com.fundatec.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banco_id")
    private Integer id;

    @JsonIgnore
    @JsonManagedReference(value = "banco_conta")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<Conta> contas;

    @Column(name = "nome")
    private String nome;

    private String senha;
}