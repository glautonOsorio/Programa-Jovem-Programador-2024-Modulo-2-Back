package com.jovemcarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jovemcarro.dto.ClienteDTO;
import com.jovemcarro.entities.Cliente;
import com.jovemcarro.repository.ClienteRepository;
import com.jovemcarro.util.ClienteMapper;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<ClienteDTO> criarCliente(ClienteDTO clienteDTO) {

        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        ClienteDTO clienteSalvoDTO = ClienteMapper.toDTO(clienteRepository.save(cliente));
        return ResponseEntity.ok(clienteSalvoDTO);
    }
}
