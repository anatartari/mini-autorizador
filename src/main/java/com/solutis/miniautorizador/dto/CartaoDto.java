package com.solutis.miniautorizador.dto;

import com.solutis.miniautorizador.model.Cartao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class CartaoDto extends CartaoCriacaoDto {

    private String dataCriacao = formatarData(LocalDate.now());
    private String validade = formatarData(LocalDate.now().plusYears(3).plusMonths(10));

    public CartaoDto(Cartao cartao) {
        this.setNumeroCartao(cartao.getNumeroCartao());
        this.setSenha(cartao.getSenha());
        this.dataCriacao = formatarData(cartao.getDataCriacao());
        this.validade = formatarData(cartao.getValidade());
    }

    public static String formatarData(LocalDate data){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public CartaoDto() {
    }
}
