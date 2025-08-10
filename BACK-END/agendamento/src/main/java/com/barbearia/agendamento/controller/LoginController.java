package com.barbearia.agendamento.controller;

import com.barbearia.agendamento.dto.LoginRequestDTO;
import com.barbearia.agendamento.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // A anotação @PostMapping continua a mesma
    @PostMapping
    // 2. AQUI ESTÁ A MUDANÇA PRINCIPAL:
    // Trocamos os dois @RequestParam por um @RequestBody que usa a classe
    // LoginRequestDTO
    public boolean login(@RequestBody LoginRequestDTO loginRequest) {
        System.out.println("Usuário recebido: " + loginRequest.getUsuario());
        System.out.println("Senha recebida: " + loginRequest.getSenha());
        // 3. Pegamos os dados de dentro do objeto recebido
        boolean autenticado = loginService.autenticar(loginRequest.getUsuario(), loginRequest.getSenha());
        
        return autenticado;
    }
}