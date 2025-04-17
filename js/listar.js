$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/api/filmes",
        method: "GET",
        success: function (filmes) {
            filmes.forEach(function (filme) {
                $("#tabela-filmes").append(`
                    <tr id="filme-${filme.id}">
                        <td>${filme.id}</td>
                        <td>${filme.titulo}</td>
                        <td>${filme.genero}</td>
                        <td>${filme.sinopse}</td>
                        <td>${filme.ano_lancamento}</td>
                        <td class="nota" data-id="${filme.id}">Carregando...</td>
                        <td>
                            <a href="detalhar.html?id=${filme.id}">Detalhar</a> |
                            <a href="#" onclick="removerFilme(${filme.id})">Excluir</a>
                        </td>
                    </tr>
                `);
            });

            // Agora busca as análises e associa via filme.id
            $.ajax({
                url: "http://localhost:8080/api/analises",
                method: "GET",
                success: function (analises) {
                    const notaPorFilme = {};

                    // Pega a nota da última análise para cada filme
                    analises.forEach(function (analise) {
                        if (analise.filme && analise.filme.id) {
                            notaPorFilme[analise.filme.id] = analise.nota;
                        }
                    });

                    // Atualiza a célula de nota na tabela
                    $(".nota").each(function () {
                        const filmeId = $(this).data("id");
                        const nota = notaPorFilme[filmeId];
                        $(this).text(nota !== undefined ? nota : "Sem nota");
                    });
                },
                error: function () {
                    console.error("Erro ao buscar análises");
                    $(".nota").text("Erro");
                }
            });
        }
    });
});

function removerFilme(id) {
    if (confirm("Tem certeza que deseja excluir este filme?")) {
        $.ajax({
            url: `http://localhost:8080/api/filmes/${id}`,
            method: "DELETE",
            success: function () {
                alert("Filme excluído com sucesso!");
                location.reload();
            }
        });
    }
}
