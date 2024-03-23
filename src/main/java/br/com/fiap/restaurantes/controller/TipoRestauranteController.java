package br.com.fiap.restaurantes.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.service.TipoCozinhaService;

@RestController
@RequestMapping("/tipocozinha")
public class TipoRestauranteController {
    @Autowired
    private TipoCozinhaService service;

    @GetMapping
    public ResponseEntity<Collection<TipoCozinhaDTO>> findAll(){
        var tipoCozinhas = service.findAll();
        return ResponseEntity.ok(tipoCozinhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCozinhaDTO> findById(@PathVariable Long id) {
        var tipo = service.findById(id);
        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    public ResponseEntity<TipoCozinhaDTO> save(@RequestBody TipoCozinhaDTO tipoDTO){
        tipoDTO = service.save(tipoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(tipoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCozinhaDTO> update(
            @PathVariable Long id,
            @RequestBody TipoCozinhaDTO tipoDTO) {

        tipoDTO = service.update(id,tipoDTO);

        return ResponseEntity.ok(tipoDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
