package com.fepho.lista_desejos_livros.controller;

import com.fepho.lista_desejos_livros.model.Livro;
import com.fepho.lista_desejos_livros.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LivroController {
    @RestController
    @RequestMapping(value = "/livros")
    class Controller{
        private LivroService livroService;

        public Controller(LivroService livroService) {
            this.livroService = livroService;
        }

        @PostMapping("/criar")
        public Livro criarLivro(@RequestBody Livro livro){
            return livroService.criarLivro(livro);
        }

        @GetMapping("/listar")
        public List<Livro> listarLivro(){
            return livroService.listarLivros();
        }

        @GetMapping("/listar/{id}")
        public Livro listarLivroPorId(@PathVariable Long id){
            return livroService.listarLivrosPorId(id);
        }

        @PutMapping("alterar/{id}")
        public Livro atualizarLivroPorId(@PathVariable Long id, @RequestBody Livro livroAtualizado){
            return livroService.atualizaLivro(id, livroAtualizado);
        }

        @DeleteMapping("/deletar/{id}")
        public void deletarLivroPorId(@PathVariable Long id){
            livroService.deletarLivroPorId(id);
        }
    }

}
