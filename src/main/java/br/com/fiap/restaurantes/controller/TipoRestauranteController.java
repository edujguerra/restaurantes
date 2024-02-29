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
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tipocozinha")
public class TipoRestauranteController {
    @Autowired
    private TipoCozinhaService service;

    @GetMapping
    @Operation(summary = "Listar todos os tipos", description = "Lista todos tipos cadastrados.")
    public ResponseEntity<Collection<TipoCozinhaDTO>> findAll(){
        var tipoCozinhas = service.findAll();
        return ResponseEntity.ok(tipoCozinhas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os tipos por ID", description = "Obtem os tipos com base no ID fornecido.")
    public ResponseEntity<TipoCozinhaDTO> findById(@PathVariable Long id) {
        var tipo = service.findById(id);
        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    @Operation(summary = "Registra um tipo", description = "Registra um tipo, conforme exemplo abaixo.")
    public ResponseEntity<TipoCozinhaDTO> save(@RequestBody TipoCozinhaDTO tipoDTO){
        tipoDTO = service.save(tipoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(tipoDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo por ID", description = "Atualiza dados de um tipo com base no seu id, conforme exemplo abaixo.")
    public ResponseEntity<TipoCozinhaDTO> update(
            @PathVariable Long id,
            @RequestBody TipoCozinhaDTO tipoDTO) {

        tipoDTO = service.update(id,tipoDTO);

        return ResponseEntity.ok(tipoDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados de um tipo por ID", description = "Apaga o registro de um tipo com base no seu ID, desregistrando-o do sistema.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
