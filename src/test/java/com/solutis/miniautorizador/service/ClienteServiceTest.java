package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.dto.ClienteCriacaoDto;
import com.solutis.miniautorizador.dto.ClienteDto;
import com.solutis.miniautorizador.dto.EnderecoCriacaoDto;
import com.solutis.miniautorizador.model.Cliente;
import com.solutis.miniautorizador.model.Endereco;
import com.solutis.miniautorizador.repository.ClienteRepository;
import com.solutis.miniautorizador.repository.EnderecoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void deveriaRetornarClienteCriado(){
        ClienteCriacaoDto clienteRequest = new ClienteCriacaoDto("11122233344", new EnderecoCriacaoDto("logradouro", "cidade", "Estado", "complemento"));

        ClienteDto clienteDto = clienteService.criar(clienteRequest);

        Cliente cliente = clienteRepository.findById(clienteDto.getId()).get();
        Endereco endereco = enderecoRepository.findById(clienteDto.getEndereco().getId()).get();

        assertEquals(cliente.getId(), clienteDto.getId());
        assertEquals(endereco.getId(), clienteDto.getEndereco().getId());

    }

}
