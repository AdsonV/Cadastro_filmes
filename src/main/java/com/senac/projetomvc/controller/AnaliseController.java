package com.senac.projetomvc.controller;

import com.senac.projetomvc.model.AnaliseModel;
import com.senac.projetomvc.repository.AnaliseRepository;
import com.senac.projetomvc.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.function.Function;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/analises")
public class AnaliseController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public ResponseEntity<List<AnaliseModel>> listarAnalises() {
        List<AnaliseModel> analises = analiseRepository.findAll();
        return ResponseEntity.ok(analises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnaliseModel> buscarAnalisePorId(@PathVariable Long id) {
        return analiseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/filme/{idFilme}")
    public ResponseEntity<AnaliseModel> criarAnalise(@PathVariable Long idFilme, @RequestBody AnaliseModel analise) {
        return filmeRepository.findById(idFilme)
                .map(filme -> {
                    analise.setFilme(filme);
                    AnaliseModel novaAnalise = analiseRepository.save(analise);
                    return ResponseEntity.ok(novaAnalise);
                })
                .orElse(ResponseEntity.notFound().build());
    }

   @DeleteMapping("/{id}")
public ResponseEntity<Void> deletarAnalise(@PathVariable Long id) {
    Function<AnaliseModel, ResponseEntity<Void>> deletar = analise -> {
        analiseRepository.delete(analise);
        return ResponseEntity.ok().build();
    };

    return analiseRepository.findById(id)
            .map(deletar)
            .orElse(ResponseEntity.notFound().build());
}

@PutMapping("/{id}")
public ResponseEntity<AnaliseModel> atualizarAnalise(@PathVariable Long id, @RequestBody AnaliseModel novaAnalise) {
    return analiseRepository.findById(id)
            .map(analise -> {
                analise.setTexto(novaAnalise.getTexto());
                analise.setNota(novaAnalise.getNota());
                return ResponseEntity.ok(analiseRepository.save(analise));
            })
            .orElse(ResponseEntity.notFound().build());
}
}