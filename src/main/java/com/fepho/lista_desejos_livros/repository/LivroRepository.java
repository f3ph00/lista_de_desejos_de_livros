package com.fepho.lista_desejos_livros.repository;

import com.fepho.lista_desejos_livros.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {
}
