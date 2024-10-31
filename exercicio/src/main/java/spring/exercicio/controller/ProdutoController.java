package spring.exercicio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.exercicio.dto.ProdutoDTO;
import spring.exercicio.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    public ResponseEntity<ProdutoDTO> addProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.criarProduto(produtoDTO);
    }

    @GetMapping
    public List<ProdutoDTO> getProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable("id") Long id) {
        return produtoService.listarProdutoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable("id") Long id) {
        boolean produtoDeletado = produtoService.deleteProduto(id);
        if (produtoDeletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable("id") Long id,
            @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoDTO> updatedProduto = produtoService.atualizarProduto(id, produtoDTO);
        return updatedProduto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
