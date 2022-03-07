package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.exception.HandleException;
import com.solutis.miniautorizador.model.Cartao;
import com.solutis.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private HandleException handleException;

    public CartaoDto criar(CartaoDto cartaoDto) {

        Cartao cartao = cartaoRepository.existsById(cartaoDto.getNumeroCartao()) ? handleException.throwCartaoExistente()
                : cartaoRepository.save(new Cartao(cartaoDto));

        return new CartaoDto(cartao);
    }

    public Optional<Double> obterSaldo(String numeroDeCartaoExistente) {

        Optional<Double> saldoDoCartao = cartaoRepository.obterSaldoDoCartao(numeroDeCartaoExistente);

        return saldoDoCartao;
    }
}
