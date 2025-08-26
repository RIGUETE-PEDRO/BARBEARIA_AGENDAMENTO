package com.barbearia.agendamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbearia.agendamento.entity.CadastroUsuario;

public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuario, Integer> {
        Optional<CadastroUsuario> findByEmail(String email);
}
