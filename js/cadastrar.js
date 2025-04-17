$(document).ready(function () {
    $("#form-cadastrar-filme").submit(function (e) {
        e.preventDefault();

        const novoFilme = {
            titulo: $("#titulo").val(),
            genero: $("#genero").val(),
            sinopse: $("#sinopse").val(),
            ano_lancamento: $("#ano_lancamento").val()
        };

        $.ajax({
            url: "http://localhost:8080/api/filmes",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(novoFilme),
            success: function () {
                alert("Filme cadastrado com sucesso!");
                window.location.href = "http://127.0.0.1:5500/paginas/listar.html";
            },
            error: function () {
                alert("Erro ao cadastrar filme. Verifique os dados e tente novamente.");
            }
        });
    });
});