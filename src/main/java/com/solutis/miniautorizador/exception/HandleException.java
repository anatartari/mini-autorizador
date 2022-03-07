package com.solutis.miniautorizador.exception;

import com.solutis.miniautorizador.model.Cartao;
import org.springframework.stereotype.Component;

@Component
public class HandleException {

    public Cartao throwCartaoExistente(){
        throw new CartaoExistenteException();
    }

}
