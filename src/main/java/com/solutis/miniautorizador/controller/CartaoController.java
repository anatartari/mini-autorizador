package com.solutis.miniautorizador.controller;

import com.solutis.miniautorizador.dto.CartaoCriacaoDto;
import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.dto.TransacaoDto;
import com.solutis.miniautorizador.exception.CartaoExistenteException;
import com.solutis.miniautorizador.exception.CartaoInexistenteException;
import com.solutis.miniautorizador.exception.SaldoInsuficienteException;
import com.solutis.miniautorizador.exception.SenhaIncorretaException;
import com.solutis.miniautorizador.service.CartaoService;
import com.solutis.miniautorizador.utils.ValidacoesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/cartoes")
    public ResponseEntity<Object> criar(@RequestBody @Valid CartaoCriacaoDto cartaoDto){

        try {
            return new ResponseEntity<Object>(cartaoService.criar(cartaoDto), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<Object>(cartaoDto, HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }

    @GetMapping("/cartoes/{numeroCartao}")
    public ResponseEntity<Double> obterSaldo(@PathVariable(required = true) String numeroCartao){
        Optional<Double> saldo = cartaoService.obterSaldo(numeroCartao);

        return saldo.isPresent() ? new ResponseEntity<Double>(saldo.get(), HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cartoes/cliente/{cpf}")
    public ResponseEntity<List<CartaoDto>> obterCartaoPorCliente(@PathVariable(required = false) String cpf){
        List<CartaoDto> listaCartoes = cartaoService.obterCartaoPorCliente(cpf);

        return listaCartoes == null ? ResponseEntity.notFound().build() : new ResponseEntity<List<CartaoDto>>(listaCartoes, HttpStatus.OK);
    }

    @PostMapping("/transacoes")
    public ResponseEntity<String> realizarTransacao(@RequestBody @Valid TransacaoDto transacao){
        try {
            return new ResponseEntity<String>(cartaoService.realizarTransacao(transacao), HttpStatus.CREATED);
        }
        catch (CartaoInexistenteException e){
            return new ResponseEntity<String>(ValidacoesEnum.CARTAO_INEXISTENTE.getMensagemDeErro(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (SenhaIncorretaException e){
            return new ResponseEntity<String>(ValidacoesEnum.SENHA_INVALIDA.getMensagemDeErro(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (SaldoInsuficienteException e){
            return new ResponseEntity<String>(ValidacoesEnum.SALDO_INSUFICIENTE.getMensagemDeErro(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
