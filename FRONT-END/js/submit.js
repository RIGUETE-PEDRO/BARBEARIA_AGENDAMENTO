// Seleciona os elementos do formulário
const form = document.querySelector('form.box');
const senhaInput = document.getElementById('senha');
const confInput = document.getElementById('confirmaSenha');
const textoForca = document.getElementById('texto-senha');
const textoConfirma = document.getElementById('text-confirma');

// Função central para validar as senhas
function validarSenhas() {
  const senha = senhaInput.value;
  const confirmaSenha = confInput.value;

  // 1. Validação do tamanho da senha
  if (senha.length > 0 && senha.length < 8) {
    textoForca.textContent = '❌ A senha deve ter no mínimo 8 caracteres.';
    textoForca.style.color = 'red';
  } else if (senha.length >= 8) {
    textoForca.textContent = '✔️ Senha com tamanho válido!';
    textoForca.style.color = 'rgb(0, 255, 81)';
  } else {
    // Limpa a mensagem se o campo estiver vazio
    textoForca.textContent = '';
  }

  // 2. Validação da confirmação de senha
  if (confirmaSenha.length > 0 && senha !== confirmaSenha) {
    textoConfirma.textContent = '❌ As senhas não conferem.';
    textoConfirma.style.color = 'red';
  } else if (confirmaSenha.length > 0 && senha === confirmaSenha) {
    textoConfirma.textContent = '✔️ As senhas conferem!';
    textoConfirma.style.color = 'rgb(0, 255, 81)';
  } else {
    // Limpa a mensagem se o campo estiver vazio
    textoConfirma.textContent = '';
  }
}

// Adiciona os "escutadores" de eventos para validação em tempo real
senhaInput.addEventListener('input', validarSenhas);
confInput.addEventListener('input', validarSenhas);

// Validação final ao tentar enviar o formulário
form.addEventListener('submit', (e) => {
  const senha = senhaInput.value;
  const confirmaSenha = confInput.value;

  // Verifica se alguma das condições de erro é verdadeira
  if (senha.length < 8 || senha !== confirmaSenha) {
    // Impede o envio do formulário
    e.preventDefault(); 
    
    // Roda a validação novamente para garantir que as mensagens de erro apareçam
    validarSenhas();
    
    // Foca no primeiro campo com erro
    if (senha.length < 8) {
      senhaInput.focus();
    } else {
      confInput.focus();
    }
  }
});

//visibilidade para cadastro

// Seleciona TODOS os ícones de visibilidade da página
const visibilityIcons = document.querySelectorAll('.visibility-icon');

// Itera sobre cada ícone encontrado
visibilityIcons.forEach(icon => {
  icon.addEventListener('click', () => {
    const passwordInput = icon.parentElement.querySelector('input');

    if (passwordInput.type === 'password') {
      // Se a senha está escondida, AÇÃO: MOSTRAR
      passwordInput.type = 'text'; 
      // O ícone agora indica a próxima ação: ESCONDER (ícone de olho aberto)
      icon.textContent = 'visibility_off'; // <<< MUDANÇA AQUI
    } else {
      // Se a senha está visível, AÇÃO: ESCONDER
      passwordInput.type = 'password'; 
      // O ícone agora indica a próxima ação: MOSTRAR (ícone de olho cortado)
      icon.textContent = 'visibility'; // <<< MUDANÇA AQUI
    }
  });
})

