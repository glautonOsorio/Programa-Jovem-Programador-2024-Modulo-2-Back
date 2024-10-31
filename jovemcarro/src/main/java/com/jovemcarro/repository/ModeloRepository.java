package com.jovemcarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemcarro.entities.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}
