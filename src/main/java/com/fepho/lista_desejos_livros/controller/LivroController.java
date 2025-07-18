package com.fepho.lista_desejos_livros.controller;

import com.fepho.lista_desejos_livros.dto.LivroDTO;
import com.fepho.lista_desejos_livros.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@AllArgsConstructor
public class LivroController {

        private LivroService livroService;

        @PostMapping("/adicionar")
        @Operation(summary = "Adicionar um novo livro", description = "Rota adicionar um novo livro e insere no banco de dados")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Livro adicionado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Erro na adição do livro")
        })
        public ResponseEntity<String> adicionarLivro(@RequestBody LivroDTO livro) {
            LivroDTO novoLivro = livroService.criarLivro(livro);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Livro adicionado com sucesso: " + novoLivro.getTitulo() + " (ID): " + novoLivro.getId());
        }

        @GetMapping("/listar")
        @Operation(summary = "Lista todos os livros", description = "Rota lista todos os livros")
        public ResponseEntity<List<LivroDTO>> listarLivro() {
            List<LivroDTO> livros = livroService.listarLivros();
            return ResponseEntity.ok(livros);
        }


        @GetMapping("/listar/{id}")
        @Operation(summary = "Lista o livro por Id", description = "Rota lista um livro pelo seu id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Livro não encontrado")
        })
        public ResponseEntity<?> listarLivrosPorId(@PathVariable Long id) {
            LivroDTO livro = livroService.listarLivrosPorId(id);
            if (livro != null) {
                return ResponseEntity.ok(livro);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("O livro com o id: " + id + " nao existe nos nossos registros");
            }
        }


        @PutMapping("/alterar/{id}")
        @Operation(summary = "Altera o livro por Id", description = "Rota altera um livro pelo seu id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Livro alterado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Livro nao encontrado, nao foi possivel alterar")
        })
        public ResponseEntity<?> alterarLivroPorId(
                @Parameter(description = "Usuario manda o id no caminho da requisiçao")
                @PathVariable Long id,
                @Parameter(description = "Usuario manda os dados do livro a ser atualizado no corpo da requisiçao")
                @RequestBody LivroDTO livroAtualizado) {

            LivroDTO livro = livroService.atualizarLivro(id, livroAtualizado);
            if (livro != null) {
                return ResponseEntity.ok(livro);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("O Livro com o id: " + id + " nao existe nos nossos registros");
            }
        }

        @DeleteMapping("/deletar/{id}")
        @Operation(summary = "Deletar o livro por Id", description = "Rota delta um livro pelo seu id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Livro nao encontrado, nao foi possivel deletar")
        })
        public ResponseEntity<String> deletarLivroPorId(@PathVariable Long id) {
            if (livroService.listarLivrosPorId(id) != null) {
                livroService.deletarLivroPorId(id);
                return ResponseEntity.ok("O Livro com o ID " + id + " deletado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("O livro com o id " + id + " nao encontrado");
            }
        }

}