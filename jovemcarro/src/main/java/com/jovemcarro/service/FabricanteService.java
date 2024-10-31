package com.jovemcarro.service;

import com.jovemcarro.dto.FabricanteDTO;
import com.jovemcarro.entities.Fabricante;
import com.jovemcarro.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    public ResponseEntity<FabricanteDTO> addFabricante(FabricanteDTO fabricanteDTO) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(fabricanteDTO.getNome());
        fabricante.setNacionalidade(fabricanteDTO.getNacionalidade());
        try {
            Fabricante fabricanteSalvo = fabricanteRepository.save(fabricante);
            fabricanteDTO.setId(fabricanteSalvo.getId());
            return ResponseEntity.ok(fabricanteDTO);
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace for debugging
            throw new RuntimeException("Erro ao salvar o fabricante", e);
        }
    }

    public List<FabricanteDTO> getAllFabricantes() {
        List<Fabricante> fabricantes = fabricanteRepository.findAll();
        return fabricantes.stream()
                .map(fabricante -> new FabricanteDTO(fabricante.getId(),
                        fabricante.getNome(), fabricante.getNacionalidade()))
                .collect(Collectors.toList());
    }

    public Optional<FabricanteDTO> getFabricanteById(Long id) {
        return fabricanteRepository.findById(id)
                .map(fabricante -> new FabricanteDTO(fabricante.getId(),
                        fabricante.getNome(), fabricante.getNacionalidade()));
    }

    public boolean deleteFabricante(Long id) {
        Optional<Fabricante> fabricanteExiste = fabricanteRepository.findById(id);
        if (fabricanteExiste.isPresent()) {
            fabricanteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<FabricanteDTO> updateFabricante(Long id, FabricanteDTO fabricanteDTO) {
        return fabricanteRepository.findById(id).map(fabricante -> {
            fabricante.setNome(fabricanteDTO.getNome());
            fabricante.setNacionalidade(fabricanteDTO.getNacionalidade());
            fabricanteRepository.save(fabricante);
            return new FabricanteDTO(fabricante.getId(), fabricante.getNome(), fabricante.getNacionalidade());
        });
    }

    public List<FabricanteDTO> getFabricantesByNacionalidade(String nacionalidade) {
        List<Fabricante> fabricantes = fabricanteRepository.findByNacionalidade(nacionalidade);
        return fabricantes.stream()
                .map(fabricante -> new FabricanteDTO(fabricante.getId(),
                        fabricante.getNome(), fabricante.getNacionalidade()))
                .collect(Collectors.toList());
    }

    public List<FabricanteDTO> getFabricantesByNacionalidadeLike(String nacionalidade) {
        List<Fabricante> fabricantes = fabricanteRepository.findByNacionalidadeContaining(nacionalidade);
        return fabricantes.stream()
                .map(fabricante -> new FabricanteDTO(fabricante.getId(),
                        fabricante.getNome(), fabricante.getNacionalidade()))
                .collect(Collectors.toList());
    }

    public Long getFabricantesByNacionalidadeCount(String nacionalidade) {
        return fabricanteRepository.countByNacionalidade(nacionalidade);
    }

    public List<FabricanteDTO> getFabricantesByNacionalidadeNot(String nacionalidade) {
        List<Fabricante> fabricantes = fabricanteRepository.findByNacionalidadeNot(nacionalidade);
        return fabricantes.stream()
                .map(fabricante -> convertToDTO(fabricante))
                .collect(Collectors.toList());
    }

    private FabricanteDTO convertToDTO(Fabricante fabricante) {
        return new FabricanteDTO(fabricante.getId(), fabricante.getNome(), fabricante.getNacionalidade());
    }
}
