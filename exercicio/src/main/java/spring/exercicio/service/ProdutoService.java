package spring.exercicio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.exercicio.dto.CategoriaDTO;
import spring.exercicio.dto.ProdutoDTO;
import spring.exercicio.entities.Categoria;
import spring.exercicio.entities.Produto;
import spring.exercicio.repository.CategoriaRepository;
import spring.exercicio.repository.ProdutoRepository;
import spring.exercicio.utils.CategoriaMapper;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<ProdutoDTO> criarProduto(ProdutoDTO produtoRecebido) {

        Categoria categoria = categoriaRepository.findById(produtoRecebido.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(produtoRecebido.getNome());
        produto.setDescricao(produtoRecebido.getDescricao());
        produto.setCategoria(categoria);

        Produto produtoSalvo = produtoRepository.save(produto);

        CategoriaDTO categoriaDTO = CategoriaMapper.categoriaToDTO(categoria);

        produtoRecebido.setCategoria(categoriaDTO);
        produtoRecebido.setId(produtoSalvo.getId());

        return ResponseEntity.ok(produtoRecebido);
    }

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        CategoriaMapper.categoriaToDTO(produto.getCategoria())))
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> listarProdutoPorId(Long id) {
        return produtoRepository.findById(id).map(
                produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        CategoriaMapper.categoriaToDTO(produto.getCategoria())));
    }

    public boolean deleteProduto(Long id) {
        Optional<Produto> produtoExiste = produtoRepository.findById(id);
        if (produtoExiste.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<ProdutoDTO> atualizarProduto(Long id, ProdutoDTO produtoRecebido) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            Categoria categoria = categoriaRepository.findById(produtoRecebido.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            produtoExistente.setNome(produtoRecebido.getNome());
            produtoExistente.setDescricao(produtoRecebido.getDescricao());
            produtoExistente.setCategoria(categoria);

            Produto produtoAtualizado = produtoRepository.save(produtoExistente);

            CategoriaDTO categoriaDTO = CategoriaMapper.categoriaToDTO(categoria);

            produtoRecebido.setCategoria(categoriaDTO);
            produtoRecebido.setId(produtoAtualizado.getId());

            return new ProdutoDTO(
                    produtoRecebido.getId(),
                    produtoRecebido.getNome(),
                    produtoRecebido.getDescricao(),
                    produtoRecebido.getCategoria());
        });
    }

}
