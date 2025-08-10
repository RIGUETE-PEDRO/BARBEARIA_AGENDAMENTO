// Seleciona o formulário
const form = document.querySelector("form.box");

form.addEventListener("submit", (e) => {
  e.preventDefault(); // evita recarregar a página

  const usuario = form.email.value;
  const senhaOriginal = form.senha.value;

  // Envia direto a senha (confie na criptografia do HTTPS)
  fetch("http://localhost:8080/login", { // trocar para https em produção
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ usuario, senha: senhaOriginal }),
  })
    .then((res) => {
      if (!res.ok) throw new Error("Usuario ou senha errada");
      return res.text();
      })
    .then((data) => { // 'data' aqui será a string "true" se o login for bem-sucedido
        
        console.log("Resposta do servidor:", data);

        // Verifica se a resposta do servidor é "true"
        if (data === "true") {
            // --- INÍCIO DA ALTERAÇÃO ---
            
            // Exibe um alerta rápido (opcional)
            alert("Login realizado com sucesso! Redirecionando...");

            // Redireciona para a nova página
            window.location.href = "paginaCentral.html"; // <-- COLOQUE A URL DA SUA PÁGINA AQUI

            // --- FIM DA ALTERAÇÃO ---
        } else {
            // Se a resposta não for "true", trata como erro
            throw new Error("Resposta inesperada do servidor.");
        }
    })
    .catch((err) => {
        console.error("Erro no login:", err.message);
        alert(err.message); // Exibe "Usuário ou senha inválidos" ou outra mensagem de erro
    });
});




////parte de visualizar senha

// Seleciona o ícone de visibilidade e o campo de senha
const visibilityIcon = document.querySelector('.visibility-icon');
const passwordInput = document.querySelector('.input-password input');

// Adiciona um "escutador" de evento de clique no ícone
visibilityIcon.addEventListener('click', () => {

  // Verifica se o tipo do campo de senha é "password"
  if (passwordInput.type === 'password') {
    // Se for, muda para "text" para mostrar a senha
    passwordInput.type = 'text';
    // E troca o ícone para o "olho cortado"
    visibilityIcon.textContent = 'visibility_off';
  } else {
    // Se não for (ou seja, se for "text"), muda de volta para "password"
    passwordInput.type = 'password';
    // E troca o ícone de volta para o "olho normal"
    visibilityIcon.textContent = 'visibility';
  }
  
});