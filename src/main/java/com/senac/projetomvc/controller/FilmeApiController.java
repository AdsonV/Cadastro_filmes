package com.senac.projetomvc.controller;

import com.senac.projetomvc.model.FilmeModel;
import com.senac.projetomvc.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/filmes")
public class FilmeApiController {
    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public List<FilmeModel> listarFilmes() { return filmeRepository.findAll(); }

    @PostMapping
    public FilmeModel salvarFilme(@RequestBody FilmeModel filme) {
        return filmeRepository.save(filme);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeModel> detalharFilme(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeModel> atualizarFilme(@PathVariable Long id, @RequestBody FilmeModel novoFilme) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setTitulo(novoFilme.getTitulo());
                    filme.setGenero(novoFilme.getGenero());
                    filme.setAno_lancamento(novoFilme.getAno_lancamento());
                    filme.setSinopse(novoFilme.getSinopse());
                    return ResponseEntity.ok(filmeRepository.save(filme));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFilme(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filmeRepository.delete(filme);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().<Void>build());
    }
}