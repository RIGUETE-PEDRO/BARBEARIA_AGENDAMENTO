package com.barbearia.agendamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIPO_USUARIO")
public class TipoUsuario {

    @Id
    private Integer id;

    @Column(name = "CARGO", unique = true, nullable = false)
    private String cargo; // Ex.: "Cliente", "Barbeiro", "Admin"

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
