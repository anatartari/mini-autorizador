package com.solutis.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class TransacaoDto {

    @NotNull
    @NotEmpty
    private String numeroCartao;

    @NotNull(message = "Teste")
    @NotEmpty
    private String senha;

    @NotEmpty
    @NotNull
    @Min(value = 0)
    private Double valor;

    public TransacaoDto(String numeroCartao, String senha, Double valor) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.valor = valor;
    }

    public TransacaoDto() {
    }
}
