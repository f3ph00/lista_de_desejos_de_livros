package com.fepho.lista_desejos_livros.service;

import com.fepho.lista_desejos_livros.dto.LivroDTO;
import com.fepho.lista_desejos_livros.mapper.LivroMapper;
import com.fepho.lista_desejos_livros.model.LivroModel;
import com.fepho.lista_desejos_livros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    //Listar todos os meus livros
    public List<LivroDTO> listarLivros(){
        List<LivroModel> livros = livroRepository.findAll();
        return livros.stream()
                .map(livroMapper::map)
                .collect(Collectors.toList());
    }

    //Listar meus livros por id
    public LivroDTO listarLivrosPorId(Long id){
        Optional<LivroModel> livroPorId = livroRepository.findById(id);
        return livroPorId.map(livroMapper::map).orElse(null);
    }

    //Criar um livro
    public LivroDTO criarLivro(LivroDTO livroDTO) {
        LivroModel livro = livroMapper.map(livroDTO);
        livro = livroRepository.save(livro);
        return livroMapper.map(livro);
    }

    //Atualiza livro
    public LivroDTO atualizarLivro(Long id, LivroDTO livroDTO){
        Optional<LivroModel> livroExistente = livroRepository.findById(id);
        if (livroExistente.isPresent()) {
            LivroModel livroAtualizado = livroMapper.map(livroDTO);
            livroAtualizado.setId(id);
            LivroModel livroSalvo = livroRepository.save(livroAtualizado);
            return livroMapper.map(livroAtualizado);
        }
        return null;
    }

    //Deletar um livro
    public void deletarLivroPorId(Long id){
        livroRepository.deleteById(id);
    }
}
