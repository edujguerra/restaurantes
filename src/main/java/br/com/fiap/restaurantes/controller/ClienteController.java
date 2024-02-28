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

import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    @Operation(summary = "Listar todos as clientes", description = "Lista todas clientes cadastradas neste parquimetro.")
    public ResponseEntity<Collection<ClienteDTO>> findAll(){
        var clientes = service.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os dados de um cliente por ID", description = "Obtem os dados de um cliente com base no ID fornecido.")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        var cliente = service.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Operation(summary = "Registra um cliente", description = "Registra um cliente, conforme exemplo abaixo.")
    public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO clienteDTO){
        clienteDTO = service.save(clienteDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(clienteDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente por ID", description = "Atualiza dados de um cliente com base no seu id, conforme exemplo abaixo.")
    public ResponseEntity<ClienteDTO> update(
            @PathVariable Long id,
            @RequestBody ClienteDTO clienteDTO) {

        clienteDTO = service.update(id,clienteDTO);

        return ResponseEntity.ok(clienteDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados de um cliente por ID", description = "Apaga o registro de um cliente com base no seu ID, desregistrando-o do sistema.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
