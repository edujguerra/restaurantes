package br.com.fiap.restaurantes.controller;

import br.com.fiap.restaurantes.dto.AvaliacaoDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.service.AvaliacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<Collection<Avaliacao>> findAll() {
        var avaliacoes = avaliacaoService.findAll();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> findById(@PathVariable Long id) {
        var avaliacao = avaliacaoService.buscarAvaliacao(id);
        return ResponseEntity.ok(avaliacao);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> save(@RequestBody Avaliacao avaliacao){
        avaliacao = avaliacaoService.criarAvaliacao(avaliacao);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> update(
            @PathVariable Long id,
            @RequestBody Avaliacao avaliacao) {

        avaliacao = avaliacaoService.alterarAvaliacao(id,avaliacao);

        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
