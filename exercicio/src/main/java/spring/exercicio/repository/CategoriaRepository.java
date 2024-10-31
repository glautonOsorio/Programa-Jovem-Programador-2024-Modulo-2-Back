package spring.exercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.exercicio.entities.Categoria;

@Repository
public interface CategoriaRepository extends  JpaRepository<Categoria,Long> {
    
}
