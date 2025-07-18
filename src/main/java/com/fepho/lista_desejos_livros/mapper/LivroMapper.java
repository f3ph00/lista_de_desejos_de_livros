package com.fepho.lista_desejos_livros.mapper;

import com.fepho.lista_desejos_livros.dto.LivroDTO;
import com.fepho.lista_desejos_livros.model.LivroModel;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {
    public LivroModel map(LivroDTO livroDTO){

        LivroModel livroModel = new LivroModel();
        livroModel.setId(livroDTO.getId());
        livroModel.setTitulo(livroDTO.getTitulo());
        livroModel.setAutor(livroDTO.getAutor());


        return livroModel;
    }

    public LivroDTO map(LivroModel livroModel){

        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livroModel.getId());
        livroDTO.setTitulo(livroModel.getTitulo());
        livroDTO.setAutor(livroModel.getAutor());


        return livroDTO;
    }
}
