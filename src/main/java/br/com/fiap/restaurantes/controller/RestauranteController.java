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

import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.service.RestauranteServiceImpl;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteServiceImpl service;

    @GetMapping
    public ResponseEntity<Collection<RestauranteDTO>> findAll(){
        var restaurantes = service.findAll();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> findById(@PathVariable Long id) {
        var restaurante = service.findById(id);
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> save(@RequestBody RestauranteDTO restauranteDTO){
    	restauranteDTO = service.save(restauranteDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(restauranteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> update(
            @PathVariable Long id,
            @RequestBody RestauranteDTO restauranteDTO) {

    	restauranteDTO = service.update(id,restauranteDTO);

        return ResponseEntity.ok(restauranteDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
