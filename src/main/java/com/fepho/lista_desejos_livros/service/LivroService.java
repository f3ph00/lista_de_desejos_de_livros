package com.fepho.lista_desejos_livros.service;

import com.fepho.lista_desejos_livros.model.Livro;
import com.fepho.lista_desejos_livros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    //Listar todos os meus livros
    public List<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    //Listar meus livros por id
    public Livro listarLivrosPorId(Long id){
        Optional<Livro> livroPorId = livroRepository.findById(id);
        return livroPorId.orElse(null);
    }

    //Criar um livro
    public Livro criarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    //Atualiza livro
    public Livro atualizaLivro(Long id, Livro livroAtualizado){
        if(livroRepository.existsById(id)){
            livroAtualizado.setId(id);
            return livroRepository.save(livroAtualizado);
        }
        return null;
    }

    //Deletar um livro
    public void deletarLivroPorId(Long id){
        livroRepository.deleteById(id);
    }
}
