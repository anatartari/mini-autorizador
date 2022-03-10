package com.solutis.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCriacaoDto {

    private String cpf;
    private EnderecoCriacaoDto endereco;

    public ClienteCriacaoDto(String cpf, EnderecoCriacaoDto endereco) {
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public ClienteCriacaoDto() {
    }
}
