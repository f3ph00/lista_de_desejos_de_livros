package com.fepho.lista_desejos_livros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;

}
