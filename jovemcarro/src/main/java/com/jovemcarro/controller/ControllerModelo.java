package com.jovemcarro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jovemcarro.dto.ModeloDTO;
import com.jovemcarro.service.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ControllerModelo {

    @Autowired
    private ModeloService modeloService;

    @PostMapping
    public ResponseEntity<ModeloDTO> addModelo(@RequestBody ModeloDTO modeloDTO) {
        return modeloService.criarModelo(modeloDTO);
    }

    @GetMapping
    public List<ModeloDTO> getModelos() {
        return modeloService.listarModelos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloDTO> getModeloById(@PathVariable("id") Long id) {
        return modeloService.getModeloById(id).map(modeloDTO -> ResponseEntity.ok(modeloDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModeloById(@PathVariable("id") Long id) {
        boolean iteDeletado = modeloService.deleteModelo(id);
        if (iteDeletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDTO> updateModelo(@PathVariable("id") Long id,
            @RequestBody ModeloDTO modeloDTO) {
        Optional<ModeloDTO> updatedModelo = modeloService.atualizarModelo(id, modeloDTO);
        return updatedModelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
