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
import br.com.fiap.restaurantes.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @GetMapping
    @Operation(summary = "Listar todos os veiculos", description = "Lista todos os veiculos registrados neste parquimetro.")
    public ResponseEntity<Collection<RestauranteDTO>> findAll(){
        var restaurantes = service.findAll();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os dados de um Restaurante por ID", description = "Obtem os dados de um Restaurante com base no ID fornecido.")
    public ResponseEntity<RestauranteDTO> findById(@PathVariable Long id) {
        var restaurante = service.findById(id);
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    @Operation(summary = "Registra um Restaurante", description = "Registra um Restaurante para um motorista ja cadastrado, conforme exemplo abaixo.")
    public ResponseEntity<RestauranteDTO> save(@RequestBody RestauranteDTO restauranteDTO){
    	restauranteDTO = service.save(restauranteDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(restauranteDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar restaurante por ID", description = "Atualiza dados de um Restaurante com base no ID, conforme exemplo abaixo.")
    public ResponseEntity<RestauranteDTO> update(
            @PathVariable Long id,
            @RequestBody RestauranteDTO restauranteDTO) {

    	restauranteDTO = service.update(id,restauranteDTO);

        return ResponseEntity.ok(restauranteDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados de um Restaurante por ID", description = "Apaga o registro de um Restaurante com base no seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
