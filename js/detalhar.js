$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const filmeId = urlParams.get("id");

    // Carrega os dados do filme
    function carregarFilme(id) {
        $.get(`http://localhost:8080/api/filmes/${id}`, function (filme) {
            $("#id").val(filme.id);
            $("#titulo").val(filme.titulo);
            $("#genero").val(filme.genero);
            $("#sinopse").val(filme.sinopse);
            $("#ano_lancamento").val(filme.ano_lancamento);

            // Carrega análises vinculadas
            $("#lista-analises").empty();
            filme.analises.forEach(function (analise) {
                $("#lista-analises").append(`
                    <li>
                        ${analise.texto} - Nota: ${analise.nota}
                        <button onclick="removerAnalise(${analise.id})">Excluir</button>
                    </li>
                `);
            });
        }).fail(function () {
            alert("Erro ao carregar os dados do filme.");
        });
    }

    carregarFilme(filmeId);

    // Atualizar filme
    $("#form-editar-filme").submit(function (e) {
        e.preventDefault();

        const filmeAtualizado = {
            id: $("#id").val(),
            titulo: $("#titulo").val(),
            genero: $("#genero").val(),
            sinopse: $("#sinopse").val(),
            ano_lancamento: $("#ano_lancamento").val(),
        };

        $.ajax({
            url: `http://localhost:8080/api/filmes/${filmeId}`,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(filmeAtualizado),
            success: function () {
                alert("Filme atualizado com sucesso!");
                window.location.href = "http://127.0.0.1:5500/paginas/listar.html";
            },
            error: function () {
                alert("Erro ao atualizar filme.");
            }
        });
    });

    // Excluir filme
    $("#btn-excluir").click(function () {
        if (confirm("Tem certeza que deseja excluir este filme?")) {
            $.ajax({
                url: `http://localhost:8080/api/filmes/${filmeId}`,
                method: "DELETE",
                success: function () {
                    alert("Filme excluído com sucesso!");
                    window.location.href = "http://127.0.0.1:5500/paginas/listar.html";
                },
                error: function () {
                    alert("Erro ao excluir filme.");
                }
            });
        }
    });

    // Adicionar nova análise
    $("#form-analise").submit(function (e) {
        e.preventDefault();

        const novaAnalise = {
            texto: $("#texto").val(),
            nota: $("#nota").val()
        };

        $.ajax({
            url: `http://localhost:8080/api/analises/filme/${filmeId}`,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(novaAnalise),
            success: function () {
                alert("Análise adicionada com sucesso!");
                carregarFilme(filmeId); // recarrega as análises
                $("#texto").val("");
                $("#nota").val("");
            },
            error: function () {
                alert("Erro ao adicionar análise.");
            }
        });
    });
});

// Excluir análise
function removerAnalise(analiseId) {
    if (confirm("Deseja realmente excluir esta análise?")) {
        $.ajax({
            url: `http://localhost:8080/api/analises/${analiseId}`,
            method: "DELETE",
            success: function () {
                alert("Análise excluída com sucesso!");
                location.reload();
            },
            error: function () {
                alert("Erro ao excluir análise.");
            }
        });
    }
}
