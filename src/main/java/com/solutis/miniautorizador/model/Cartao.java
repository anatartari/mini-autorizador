package com.solutis.miniautorizador.model;

import com.solutis.miniautorizador.dto.CartaoCriacaoDto;
import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.exception.HandleException;
import com.solutis.miniautorizador.utils.ValidacoesEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class Cartao {

    @Id
    @Column(unique = true)
    private String numeroCartao;
    private String senha;
    private Double saldo;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    private LocalDate validade;
    @ManyToOne
    private Cliente cliente;

    public Cartao(CartaoCriacaoDto cartaoCriacaoDto) {
        this.numeroCartao = cartaoCriacaoDto.getNumeroCartao();
        this.senha = cartaoCriacaoDto.getSenha();
        this.saldo = 500.00;
        this.dataCriacao = LocalDate.now();
        this.validade = LocalDate.now().plusYears(3).plusMonths(10);
    }

    public void validarAtribuirNovoSaldo(Double valor, HandleException handleException) {
        this.saldo = this.saldo >= valor ? this.saldo - valor :
                (Double) handleException.throwExcecaoDeValidacao(ValidacoesEnum.SALDO_INSUFICIENTE);
    }

    public Cartao(String numeroCartao, String senha, double saldo) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Cartao(CartaoDto cartaoDto) {
        this.numeroCartao = cartaoDto.getNumeroCartao();
        this.senha = cartaoDto.getSenha();
        this.saldo = 500.00;
        this.dataCriacao = formatarString(cartaoDto.getDataCriacao());
        this.validade = formatarString(cartaoDto.getValidade());
    }

    public static LocalDate formatarString(String data) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate dataFormatada = null;
        try {
            dataFormatada = formater.parse(data).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dataFormatada;
    }

    public Cartao() {
    }


}
