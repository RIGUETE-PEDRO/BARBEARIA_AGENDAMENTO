package com.barbearia.agendamento.service;
import org.springframework.stereotype.Service;

import com.barbearia.agendamento.entity.CadastroUsuario;
import com.barbearia.agendamento.repository.CadastroUsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service // Define que esta classe é um serviço gerenciado pelo Spring
public class LoginService {

    // Repositório para acessar o banco
    private final CadastroUsuarioRepository usuarioRepository;
    
    // Classe que faz comparação de senhas criptografadas com BCrypt
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    // Injeção de dependência pelo construtor
    public LoginService(CadastroUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        
    }

    // Método para autenticar um usuário
    public boolean autenticar(String email, String senhaDigitada) {

        // Busca o usuário pelo email
        Optional<CadastroUsuario> usuarioOpt = usuarioRepository.findByEmail(email);

        // Se não encontrou, retorna falso
        if (usuarioOpt.isEmpty()) {
            System.out.println("DEBUG: Usuário com email '" + email + "' não encontrado.");
            return false;
        }
        
        // Pega o usuário encontrado
        CadastroUsuario usuario = usuarioOpt.get();

        // --- INÍCIO DO CÓDIGO DE DEPURAÇÃO ---
        // Adicione estas linhas para ver os valores no console
        System.out.println("=================================================");
        System.out.println("==> INICIANDO DEPURAÇÃO DE LOGIN PARA: " + email);
        System.out.println("==> Senha Original (Digitada): " + senhaDigitada);
        System.out.println("==> Hash Salva no Banco de Dados: " + usuario.getSenha());
        
        // Vamos também verificar o resultado da comparação antes de retornar
        boolean senhasCombinam = passwordEncoder.matches(senhaDigitada, usuario.getSenha());
        System.out.println("==> Resultado da Comparação (matches): " + senhasCombinam);
        System.out.println("=================================================");
        // --- FIM DO CÓDIGO DE DEPURAÇÃO ---

        // Retorna o resultado da comparação
        return senhasCombinam;
    }
}