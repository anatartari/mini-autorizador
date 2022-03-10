package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.dto.ClienteCriacaoDto;
import com.solutis.miniautorizador.dto.ClienteDto;
import com.solutis.miniautorizador.exception.HandleException;
import com.solutis.miniautorizador.model.Cliente;
import com.solutis.miniautorizador.repository.ClienteRepository;
import com.solutis.miniautorizador.utils.ValidacoesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HandleException handleException;

    public ClienteDto criar(ClienteCriacaoDto clienteRequest) {

        Object clienteExistente = clienteRepository.findByCpf(clienteRequest.getCpf()).isPresent() ? handleException.throwExcecaoDeValidacao(ValidacoesEnum.CLIENTE_EXISTENTE) : true;

        Cliente cliente = clienteRepository.save(new Cliente(clienteRequest));

        return new ClienteDto(cliente);
    }

    public ClienteDto obterCliente(Integer idCliente) {

        Optional<Cliente> clienteDto = clienteRepository.findById(idCliente);

        return clienteDto.isPresent() ? new ClienteDto(clienteDto.get()) : null;
    }
}
