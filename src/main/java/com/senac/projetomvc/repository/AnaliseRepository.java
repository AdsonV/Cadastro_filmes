package com.senac.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senac.projetomvc.model.AnaliseModel;

public interface AnaliseRepository extends JpaRepository<AnaliseModel, Long> {}