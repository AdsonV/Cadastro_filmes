package com.senac.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senac.projetomvc.model.FilmeModel;

public interface FilmeRepository extends JpaRepository<FilmeModel, Long> {}