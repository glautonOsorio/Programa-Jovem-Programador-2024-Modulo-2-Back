package spring.exercicio.utils;

import spring.exercicio.dto.CategoriaDTO;
import spring.exercicio.entities.Categoria;

public class CategoriaMapper {

    public static CategoriaDTO categoriaToDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao());
    }

    public static Categoria categoriaToEntity(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        return categoria;
    }
}
