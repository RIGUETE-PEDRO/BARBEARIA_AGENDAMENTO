package com.barbearia.agendamento.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbearia.agendamento.dto.CadastroDTO;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @PostMapping
    public String cadastrarUsuario(@RequestBody CadastroDTO cadastro) {
        System.out.println("Usuário: ");
        System.out.println("Nome: ");
        System.out.println("Senha: ");

        // Aqui você colocaria lógica para salvar no banco, etc.
        return "Cadastro recebido com sucesso!";
    }

}