package com.solutis.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClienteDto extends ClienteCriacaoDto {

    public Integer id;
    public String data_inicio;
    List<CartaoDto> cartoes = new ArrayList<>();
    EnderecoDto endereco;

    public ClienteDto(String cpf, Integer id, String data_inicio, List<CartaoDto> cartoes, EnderecoDto endereco) {
        this.setCpf(cpf);
        this.id = id;
        this.data_inicio = data_inicio;
        this.cartoes = cartoes;
        this.endereco = endereco;
    }

    public ClienteDto(){}
}
