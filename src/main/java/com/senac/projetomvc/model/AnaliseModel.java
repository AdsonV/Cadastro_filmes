package com.senac.projetomvc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "analise")
public class AnaliseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto")
    private String texto;
    
    @Column (name = "nota")
    private int nota;
    
    @ManyToOne
@JoinColumn(name = "filme_id")
@JsonIgnoreProperties("analises")
private FilmeModel filme;

}