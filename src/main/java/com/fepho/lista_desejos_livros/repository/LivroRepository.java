package com.fepho.lista_desejos_livros.repository;

import com.fepho.lista_desejos_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
