package spring.exercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.exercicio.dto.CategoriaDTO;
import spring.exercicio.entities.Categoria;
import spring.exercicio.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<CategoriaDTO> addCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        try {
            Categoria categoriaSalvo = categoriaRepository.save(categoria);
            categoriaDTO.setId(categoriaSalvo.getId());
            return ResponseEntity.ok(categoriaDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o categoria", e);
        }

    }

    public List<CategoriaDTO> getAllCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream().map(categoria -> new CategoriaDTO(categoria.getId(),
                categoria.getNome(), categoria.getDescricao())).toList();
    }

    public Optional<CategoriaDTO> getCategoriaById(Long Id) {
        return categoriaRepository.findById(Id).map(categoria -> new CategoriaDTO(categoria.getId(),
                categoria.getNome(), categoria.getDescricao()));
    }

    public boolean deleteCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        } else {
            return false;

        }
    }

    public Optional<CategoriaDTO> updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(categoriaDTO.getNome());
            categoria.setDescricao(categoriaDTO.getDescricao());
            categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
        });
    }

}
