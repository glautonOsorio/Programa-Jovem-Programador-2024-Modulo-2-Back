package com.jovemcarro.controller;

import com.jovemcarro.dto.FabricanteDTO;
import com.jovemcarro.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fabricante")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerFabricante {

    @Autowired
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<FabricanteDTO> addFabricante(@RequestBody FabricanteDTO fabricanteDTO) {
        return fabricanteService.addFabricante(fabricanteDTO);
    }

    @GetMapping
    public List<FabricanteDTO> getFabricantes() {
        return fabricanteService.getAllFabricantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> getFabricanteById(@PathVariable("id") Long id) {
        return fabricanteService.getFabricanteById(id)
                .map(fabricanteDTO -> ResponseEntity.ok(fabricanteDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFabricanteById(@PathVariable("id") Long id) {
        boolean itemDeletado = fabricanteService.deleteFabricante(id);
        if (itemDeletado) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteDTO> updateFabricante(@PathVariable("id") Long id,
            @RequestBody FabricanteDTO fabricanteDTO) {
        Optional<FabricanteDTO> updatedFabricante = fabricanteService.updateFabricante(id, fabricanteDTO);
        return updatedFabricante.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nacionalidade")
    public List<FabricanteDTO> getFabricantesByNacionalidade(@RequestParam("nacionalidade") String nacionalidade) {
        return fabricanteService.getFabricantesByNacionalidade(nacionalidade);
    }

    @GetMapping("/like/nacionalidade")
    public List<FabricanteDTO> getFabricantesByNacionalidadeLike(@RequestParam("nacionalidade") String nacionalidade) {
        return fabricanteService.getFabricantesByNacionalidadeLike(nacionalidade);
    }

    @GetMapping("/count/nacionalidade")
    public Long getFabricantesByNacionalidadeCount(@RequestParam("nacionalidade") String nacionalidade) {
        return fabricanteService.getFabricantesByNacionalidadeCount(nacionalidade);
    }

    @GetMapping("/not/nacionalidade")
    public List<FabricanteDTO> getFabricantesByNacionalidadeNot(@RequestParam("nacionalidade") String nacionalidade) {
        return fabricanteService.getFabricantesByNacionalidadeNot(nacionalidade);
    }
}
