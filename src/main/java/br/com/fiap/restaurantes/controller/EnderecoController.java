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

import br.com.fiap.restaurantes.dto.EnderecoDTO;
import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @GetMapping
    @Operation(summary = "Listar todos os enderecos", description = "Lista todos enderecos cadastrados.")
    public ResponseEntity<Collection<EnderecoDTO>> findAll(){
        var endereco = service.findAll();
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os endecos por ID", description = "Obtem os endereços com base no ID fornecido.")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        var endereco = service.findById(id);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    @Operation(summary = "Registra um endereco", description = "Registra um endereco, conforme exemplo abaixo.")
    public ResponseEntity<EnderecoDTO> save(@RequestBody EnderecoDTO enderecoDTO){
        enderecoDTO = service.save(enderecoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(enderecoDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar endereco por ID", description = "Atualiza dados de um endereço com base no seu id, conforme exemplo abaixo.")
    public ResponseEntity<EnderecoDTO> update(
            @PathVariable Long id,
            @RequestBody EnderecoDTO enderecoDTO) {

        enderecoDTO = service.update(id,enderecoDTO);

        return ResponseEntity.ok(enderecoDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados de um endereco por ID", description = "Apaga o registro de um endereco com base no seu ID, desregistrando-o do sistema.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
