//requisição para o back

const form = document.querySelector("form.box");

form.addEventListener("submit", (e) => {
  //evita recarregar a pagina
  e.preventDefault();

  const email = form.email.value;
  const senhaOriginal = form.senha.value;

  // Hash SHA-256 da senha com CryptoJS
  //estou criptografando a senha para maior segurança
  const senhaHash = CryptoJS.SHA256(senhaOriginal).toString();
  const lembrar = form.check.checked;
  //local onde se encontra o backend
  fetch("http://localhost:8080/login", {
    //metodo da requisição
    method: "POST",
    headers: { "Content-Type": "application/json" },
    //corpo da requisição
    body: JSON.stringify({ email, senha: senhaHash, lembrar }),
  })
    //metodos de resposta referente a o erro que aparecerar no console do navegador
    .then((res) => {
      if (!res.ok) throw new Error("Erro no servidor");
      return res.json();
    })
    .then((data) => {
      console.log("Sucesso:", data);
      alert("Login enviado com sucesso!");
    })
    .catch((err) => {
      console.error("Erro:", err);
      alert("Erro ao enviar o login");
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