package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.exception.HandleException;
import com.solutis.miniautorizador.model.Cartao;
import com.solutis.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private HandleException handleException;

    public CartaoDto criar(CartaoDto cartaoDto) {

        boolean cartaoValido = cartaoRepository.existsById(cartaoDto.getNumeroCartao()) ? handleException.throwCartaoExistente() : true;

        Cartao cartao = cartaoRepository.save(new Cartao(cartaoDto));

        return new CartaoDto(cartao);
    }
}
