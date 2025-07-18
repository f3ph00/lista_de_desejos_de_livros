package com.fepho.lista_desejos_livros.controller;

import com.fepho.lista_desejos_livros.dto.LivroDTO;
import com.fepho.lista_desejos_livros.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/livros/ui")
public class LivroControllerUi {

    private LivroService livroService;

    @Operation(summary = "página html com lista de livros",
            description = "página html com a lista de livros",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros listados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Erro na listagem dos livro")
            })
    @GetMapping("/listar")
    public String listarLivro(Model model){
        List<LivroDTO> livros = livroService.listarLivros();
        model.addAttribute("livros", livros);
        return "listarLivros";
    }

    @Operation(summary = "Deletar o livro por Id",
            description = "Botão para deletar um livro po Id na página html listar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Livro não encontrado, não foi possivel deletar")
            })
    @GetMapping("/deletar/{id}")
    public String deletarLivroPorId(@PathVariable Long id){
        livroService.deletarLivroPorId(id);
        return "redirect:/livros/ui/listar";
    }

    @Operation(summary = "lista o livro por Id",
            description = "Botão para deletar um livro por Id na página html listar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Livro não encontrado, não foi possivel deletar")
            })
    @GetMapping("/listar/{id}")
    public String listarLivroPorId(@PathVariable Long id, Model model){
        LivroDTO livro = livroService.listarLivrosPorId(id);
        if (livro !=null){
            model.addAttribute("livro", livro);
            return "detalheslivros";
        } else {
            model.addAttribute("mensagem", "Livro Não encontrado!");
            return "listarLivros";
        }
    }

    @Operation(summary = "Alterar o livro por Id",
            description = "Alterar informações do livro por Id na página html alterar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros alterado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Livro não encontrado, não foi possivel alterar")
            })
    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterar(@PathVariable Long id, Model model) {
        LivroDTO livro = livroService.listarLivrosPorId(id);
        if (livro != null) {
            model.addAttribute("livro", livro);
            return "alterarLivro";
        } else {
            model.addAttribute("mensagem", "Livro não encontrado");
            return "listarLivros";
        }
    }

    @Operation(summary = "Alterar o livro por Id",
            description = "Alterar informações do livro por Id na página html alterar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros alterado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Livro não encontrado, não foi possivel alterar")
            })
    @PostMapping("/alterar/{id}")
    public String alterarLivro(@PathVariable Long id, @ModelAttribute("livro") LivroDTO livroDTO, RedirectAttributes redirectAttributes) {
        try {
            livroService.atualizarLivro(id, livroDTO);
            redirectAttributes.addFlashAttribute("mensagem", "Livro alterado com sucesso!");
            return "redirect:/livros/ui/listar";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/livros/ui/alterar/" + id;
        }
    }

    @Operation(summary = "Adiciona um livro",
            description = "Adiciona informações do livro na página html",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros adicionado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na adição do livro")
            })
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarLivro(Model model) {
        model.addAttribute("livro", new LivroDTO());
        return "adicionarLivro";
    }

    @Operation(summary = "Salva um livro",
            description = "Adiciona informações do livro ao banco de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Livros salvo com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Erro na adição do livro")
            })
    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute("livro") LivroDTO livro, RedirectAttributes redirectAttributes) {
        livroService.criarLivro(livro);
        redirectAttributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");
        return "redirect:/livros/ui/listar";
    }
}
