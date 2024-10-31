package spring.exercicio.controller;

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

import spring.exercicio.dto.CategoriaDTO;
import spring.exercicio.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class ControllerCategoria {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.addCategoria(categoriaDTO);
    }

    @GetMapping
    public List<CategoriaDTO> getCategorias() {
        return categoriaService.getAllCategoria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable("id") Long id) {
        return categoriaService.getCategoriaById(id).map(categoriaDTO -> ResponseEntity.ok(categoriaDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable("id") Long id) {
        if (categoriaService.deleteCategoria(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable("id") Long id,
            @RequestBody CategoriaDTO categoriaDTO) {
        Optional<CategoriaDTO> updatedCategoria = categoriaService.updateCategoria(id, categoriaDTO);
        return updatedCategoria.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    };

}
