package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.dto.TransacaoDto;
import com.solutis.miniautorizador.exception.HandleException;
import com.solutis.miniautorizador.model.Cartao;
import com.solutis.miniautorizador.repository.CartaoRepository;
import com.solutis.miniautorizador.utils.ValidacoesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private HandleException handleException;

    public CartaoDto criar(CartaoDto cartaoDto) {

        Object cartao = cartaoRepository.existsById(cartaoDto.getNumeroCartao()) ? handleException.throwExcecaoDeValidacao(ValidacoesEnum.CARTAO_EXISTENTE)
                : cartaoRepository.save(new Cartao(cartaoDto));

        return new CartaoDto((Cartao) cartao);
    }

    public Optional<Double> obterSaldo(String numeroDeCartaoExistente) {

        Optional<Double> saldoDoCartao = cartaoRepository.obterSaldoDoCartao(numeroDeCartaoExistente);

        return saldoDoCartao;
    }

    @Transactional
    public String realizarTransacao(TransacaoDto transacao) {

        Optional<Cartao> cartaoOptional = cartaoRepository.findById(transacao.getNumeroCartao());

        Object cartaoValido = cartaoOptional.isPresent() ? true : handleException.throwExcecaoDeValidacao(ValidacoesEnum.CARTAO_INEXISTENTE);
        Cartao cartao = cartaoOptional.get();
        cartaoValido = cartao.getSenha().equals(transacao.getSenha()) ? true : handleException.throwExcecaoDeValidacao(ValidacoesEnum.SENHA_INVALIDA);
        cartao.validarAtribuirNovoSaldo(transacao.getValor(), handleException);

        return "OK";
    }
}
