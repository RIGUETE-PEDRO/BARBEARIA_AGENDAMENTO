package com.barbearia.agendamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbearia.agendamento.dto.CadastroDTO;
import com.barbearia.agendamento.entity.CadastroUsuario;
import com.barbearia.agendamento.entity.TipoUsuario;
import com.barbearia.agendamento.repository.TipoUsuarioRepository;
import com.barbearia.agendamento.service.CadastroUsuarioService;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    // Criptografia
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public String cadastrarUsuario(@RequestBody CadastroDTO cadastro) {
        CadastroUsuario usuario = new CadastroUsuario();
        usuario.setNome(cadastro.getNome());
        usuario.setEmail(cadastro.getEmail());

        // Criptografa a senha
        String senhaCriptografada = passwordEncoder.encode(cadastro.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuario.setTelefone(cadastro.getTelefone());

        // Se o front não enviar o tipoUsuarioId, usa 3 como padrão
        Integer tipoId = cadastro.getTipoUsuarioId();
        if (tipoId == null) {
            tipoId = 3;
        }

        // Busca TipoUsuario no banco
        TipoUsuario tipo = tipoUsuarioRepository.findById(tipoId)
                .orElseThrow(() -> new RuntimeException("TipoUsuario não encontrado"));
        usuario.setTipoUsuario(tipo);

        // Salva no banco
        usuarioService.salvar(usuario);

        return "Cadastro recebido com sucesso!";
    }

}
