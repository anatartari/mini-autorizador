package com.solutis.miniautorizador.dto;

import com.solutis.miniautorizador.model.Cartao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CartaoDto {

    @NotNull(message = "Teste mensagem")
    @NotEmpty
    private String numeroCartao;

    @NotNull
    @NotEmpty
    private String senha;

    public CartaoDto(String numeroDoCartao, String senha) {
        this.numeroCartao = numeroDoCartao;
        this.senha = senha;
    }

    public CartaoDto(Cartao cartao) {
        this.numeroCartao = cartao.getNumeroCartao();
        this.senha = cartao.getSenha();
    }

    public CartaoDto() {
    }
}
