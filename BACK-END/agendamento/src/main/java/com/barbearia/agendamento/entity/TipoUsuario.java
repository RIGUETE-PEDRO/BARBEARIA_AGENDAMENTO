package com.barbearia.agendamento.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // Indica que esta classe é uma entidade JPA (mapeada para tabela do banco)
@Table(name = "TIPO_USUARIO") // Nome exato da tabela no banco
public class TipoUsuario {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento no MySQL
    private Integer id;

    private String nome; // Nome do usuário

    @Column(unique = true) // Email não pode ser duplicado
    private String email;

    private String senha; // Aqui ficará a senha (idealmente criptografada)

    @Column(name = "data_criacao") // Nome da coluna no banco
    private LocalDateTime dataCriacao;

    // Getters e Setters para acessar/alterar os valores

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}
