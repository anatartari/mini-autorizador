package com.solutis.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoCriacaoDto {

    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;

    public EnderecoCriacaoDto(String logradouro, String cidade, String estado, String complemento) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }

    public EnderecoCriacaoDto() {
    }
}
