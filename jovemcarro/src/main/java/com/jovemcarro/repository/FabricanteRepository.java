package com.jovemcarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemcarro.entities.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

  List<Fabricante> findByNacionalidade(String nacionalidade);

   List<Fabricante> findByNacionalidadeContaining(String nacionalidade);

   Long countByNacionalidade(String nacionalidade);

  List<Fabricante> findByNacionalidadeNot(String nacionalidade);

}
