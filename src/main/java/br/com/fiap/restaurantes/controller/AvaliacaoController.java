package br.com.fiap.restaurantes.controller;

import br.com.fiap.restaurantes.dto.AvaliacaoRequest;
import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.service.AvaliacaoService;
import br.com.fiap.restaurantes.service.TipoCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<Collection<Avaliacao>> findAll() {
        var avaliacoes = service.listarAvaliacoes();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> findById(@PathVariable Long id) {
        var tipo = service.buscarAvaliacao(id);
        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> save(@RequestBody Avaliacao avaliacao){
        avaliacao = service.criarAvaliacao(avaliacao);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> update(
            @PathVariable Long id,
            @RequestBody Avaliacao avaliacao) {

        avaliacao = service.alterarAvaliacao(id,avaliacao);

        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.apagarAvaliacao(id);
        return ResponseEntity.noContent().build();
    }
}
