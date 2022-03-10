package com.solutis.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartaoCriacaoDto {

    @NotNull
    @NotEmpty
    private String numeroCartao;

    @NotNull
    @NotEmpty
    private String senha;

    public CartaoCriacaoDto(String numeroCartao, String senha) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }

    public CartaoCriacaoDto() {
    }
}
