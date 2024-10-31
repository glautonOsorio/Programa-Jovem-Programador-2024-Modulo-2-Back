document.addEventListener("DOMContentLoaded", () => {
  const fabricantesContainer = document.getElementById("fabricantes-container");

  function carregarFabricantes() {
    fetch("http://localhost:8080/fabricante")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Erro ao buscar os fabricantes");
        }
        return res.json();
      })
      .then((fabricantes) => {
        fabricantesContainer.innerHTML = "";

        fabricantes.forEach((fabricante) => {
          const card = document.createElement("div");
          card.classList.add("card");

          card.innerHTML = `
            <h2> ${fabricante.nome} </h2>
            <p> País: ${fabricante.nacionalidade}  </p>
            `;
          fabricantesContainer.appendChild(card);
        });
      })
      .catch((err) => {
        console.log("Erro", err);
        fabricantesContainer.innerHTML = `<p>Erro ao carregar fabricantes</p>`;
      });
  }
  carregarFabricantes();
});
