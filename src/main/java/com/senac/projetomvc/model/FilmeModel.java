package com.senac.projetomvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filme")
public class FilmeModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    private String titulo;
    
    private String sinopse;
    
    private String genero;
    
    @Column(name = "ano_lancamento")
    private int ano_lancamento;

@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
@JsonIgnoreProperties("filme") // ou use se necessário para evitar loop inverso
private List<AnaliseModel> analises;

}