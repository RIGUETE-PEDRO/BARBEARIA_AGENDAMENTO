const usuario = "Pedro henrique";

document.getElementById("UserName").innerHTML = usuario;

// Pega os elementos
  const userNameSpan = document.getElementById("UserName");
  const userMenu = document.getElementById("userMenu");

  // Quando clicar no nome, mostra/esconde o menu
  userNameSpan.addEventListener("click", () => {
    userMenu.style.display = userMenu.style.display === "block" ? "none" : "block";
  });

  // Fecha o menu se clicar fora
  document.addEventListener("click", (e) => {
    if (!e.target.closest(".usuario")) {
      userMenu.style.display = "none";
    }
  });