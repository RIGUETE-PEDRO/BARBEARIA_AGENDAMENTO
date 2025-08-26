package com.barbearia.agendamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
    @Table(name = "USUARIO")
    
    public class CadastroUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     @ManyToOne
    @JoinColumn(name = "ID_TIPO", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column (name = "NOME", nullable = false)
    private String nome;

    @Column (name = "EMAIL", nullable = false ,unique = true)
    private String email;

    @Column (name = "SENHA", nullable = false)
    private String telefone;

    @Column (name = )

}
