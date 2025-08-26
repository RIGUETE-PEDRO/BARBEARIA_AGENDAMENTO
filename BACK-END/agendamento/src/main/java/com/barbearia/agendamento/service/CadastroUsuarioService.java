package com.barbearia.agendamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbearia.agendamento.entity.CadastroUsuario;
import com.barbearia.agendamento.repository.CadastroUsuarioRepository;

@Service
public class CadastroUsuarioService {

    @Autowired
    private CadastroUsuarioRepository usuarioRepository;

    public CadastroUsuario salvar(CadastroUsuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<CadastroUsuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
