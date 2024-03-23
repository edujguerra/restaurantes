package br.com.fiap.restaurantes.controller;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    @Operation(summary = "Listar todos os veiculos", description = "Lista todos os veiculos registrados neste parquimetro.")
    public ResponseEntity<Collection<ReservaDTO>> findAll() {
        var reservas = reservaService.findAll();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
        var reserva = reservaService.findById(id);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> save(@RequestBody ReservaDTO reservaDTO) {
        reservaDTO = reservaService.save(reservaDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(reservaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> update(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        reservaDTO = reservaService.update(id, reservaDTO);
        return ResponseEntity.ok(reservaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
