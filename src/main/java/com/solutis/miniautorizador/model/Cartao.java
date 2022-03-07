package com.solutis.miniautorizador.model;

import com.solutis.miniautorizador.dto.CartaoDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Cartao {

    @Id
    @Column(unique = true)
    private String numeroCartao;
    private String senha;
    private Double saldo;

    public Cartao(String numeroCartao, String senha, double saldo) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Cartao(CartaoDto cartaoDto) {
        this.numeroCartao = cartaoDto.getNumeroCartao();
        this.senha = cartaoDto.getSenha();
        this.saldo = 500.00;
    }

    public Cartao() {
    }
}
