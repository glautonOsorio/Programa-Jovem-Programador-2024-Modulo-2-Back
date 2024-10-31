package com.jovemcarro.util;

import com.jovemcarro.dto.ClienteDTO;
import com.jovemcarro.dto.EnderecoDTO;
import com.jovemcarro.entities.Cliente;
import com.jovemcarro.entities.Endereco;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        /*
         * ClienteDTO clienteDTO = new ClienteDTO();
         * clienteDTO.setId(cliente.getId());
         * clienteDTO.setNome(cliente.getNome());
         * clienteDTO.setSexo(cliente.getSexo());
         * clienteDTO.setCpf(cliente.getCpf());
         */
        enderecoDTO.setId(cliente.getEndereco().getId());
        enderecoDTO.setCep(cliente.getEndereco().getCep());
        enderecoDTO.setLogradouro(cliente.getEndereco().getLogradouro());
        enderecoDTO.setNumero(cliente.getEndereco().getNumero());
        enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());
        enderecoDTO.setCidade(cliente.getEndereco().getCidade());
        enderecoDTO.setUf(cliente.getEndereco().getUf());

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getSexo(), cliente.getCpf(), enderecoDTO);
    }

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Endereco endereco = new Endereco();
        if (clienteDTO.getEndereco() != null) {
            endereco.setId(clienteDTO.getEndereco().getId());
            endereco.setCep(clienteDTO.getEndereco().getCep());
            endereco.setLogradouro(clienteDTO.getEndereco().getLogradouro());
            endereco.setNumero(clienteDTO.getEndereco().getNumero());
            endereco.setComplemento(clienteDTO.getEndereco().getComplemento());
            endereco.setCidade(clienteDTO.getEndereco().getCidade());
            endereco.setUf(clienteDTO.getEndereco().getUf());
        }

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(endereco);

        return cliente;
    }

}
