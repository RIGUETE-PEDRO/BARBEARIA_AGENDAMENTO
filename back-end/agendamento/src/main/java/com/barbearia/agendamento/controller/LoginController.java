package com.barbearia.agendamento.controller;

import com.barbearia.agendamento.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
// A linha abaixo é a única adição necessária para corrigir o CORS
@CrossOrigin(origins = "http://127.0.0.1:5500") 
public class LoginController {
    
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Email: " + loginRequest.getUsuario());
        System.out.println("Senha hash: " + loginRequest.getSenha());

        // Sua lógica de retorno está correta.
        return ResponseEntity.ok().body(
                new java.util.HashMap<String, String>() {
                    {
                        put("status", "ok");
                        put("mensagem", "Login realizado com sucesso!");
                    }
                });
    }
}