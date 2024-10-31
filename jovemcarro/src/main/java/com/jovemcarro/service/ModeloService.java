package com.jovemcarro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jovemcarro.dto.FabricanteDTO;
import com.jovemcarro.dto.ModeloDTO;
import com.jovemcarro.entities.Fabricante;
import com.jovemcarro.entities.Modelo;
import com.jovemcarro.repository.FabricanteRepository;
import com.jovemcarro.repository.ModeloRepository;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    public ResponseEntity<ModeloDTO> criarModelo(ModeloDTO modeloRecebido) {

        Fabricante fabricante = fabricanteRepository.findById(modeloRecebido.getFabricante().getId())
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));

        Modelo modelo = new Modelo();

        modelo.setFabricante(fabricante);
        modelo.setNome(modeloRecebido.getNome());
        Modelo modeloSalvo = modeloRepository.save(modelo);

        FabricanteDTO fabricanteDTO = new FabricanteDTO(fabricante.getId(), fabricante.getNome(),
                fabricante.getNacionalidade());

        modeloRecebido.setFabricante(fabricanteDTO);
        modeloRecebido.setIdModelo(modeloSalvo.getId());

        return ResponseEntity.ok(modeloRecebido);

    }

    public List<ModeloDTO> listarModelos() {

        List<Modelo> modelos = modeloRepository.findAll();

        return modelos.stream().map(
                modelo -> new ModeloDTO(modelo.getId(), modelo.getNome(),
                        convertFabricanteDTO(modelo.getFabricante())))
                .collect(Collectors.toList());

    }

    public Optional<ModeloDTO> getModeloById(Long id) {
        return modeloRepository.findById(id).map(
                modelo -> new ModeloDTO(modelo.getId(), modelo.getNome(),
                        convertFabricanteDTO(modelo.getFabricante())));
    }

    public boolean deleteModelo(Long id) {
        Optional<Modelo> modeloExiste = modeloRepository.findById(id);
        if (modeloExiste.isPresent()) {
            modeloRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<ModeloDTO> atualizarModelo(Long id, ModeloDTO modeloRecebido) {
        return modeloRepository.findById(id).map(modeloExistente -> {
            Fabricante fabricante = fabricanteRepository.findById(modeloRecebido.getFabricante().getId())
                    .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));

            modeloExistente.setNome(modeloRecebido.getNome());
            modeloExistente.setFabricante(fabricante);

            Modelo modeloAtualizado = modeloRepository.save(modeloExistente);

            FabricanteDTO fabricanteDTO = new FabricanteDTO(fabricante.getId(), fabricante.getNome(),
                    fabricante.getNacionalidade());

            modeloRecebido.setFabricante(fabricanteDTO);
            modeloRecebido.setIdModelo(modeloAtualizado.getId());

            return new ModeloDTO(modeloRecebido.getIdModelo(), modeloRecebido.getNome(),
                    modeloRecebido.getFabricante());
        });
    }

    private FabricanteDTO convertFabricanteDTO(Fabricante fabricante) {
        FabricanteDTO fabricanteDTO = new FabricanteDTO(fabricante.getId(),
                fabricante.getNome(), fabricante.getNacionalidade());

        return fabricanteDTO;

    }

}
