package com.solutis.miniautorizador.exception;

import com.solutis.miniautorizador.model.Cartao;
import com.solutis.miniautorizador.utils.ValidacoesEnum;
import org.springframework.stereotype.Component;

@Component
public class HandleException {

    public Object throwExcecaoDeValidacao(ValidacoesEnum erro) {
       switch (erro){
           case CARTAO_EXISTENTE:
               throw new CartaoExistenteException();
           case CARTAO_INEXISTENTE:
               throw new CartaoInexistenteException();
           case SENHA_INVALIDA:
               throw new SenhaIncorretaException();
           case SALDO_INSUFICIENTE:
               throw new SaldoInsuficienteException();
           default:
               return null;
       }
    }
}
