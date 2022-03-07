package com.solutis.miniautorizador.exception;

import org.springframework.stereotype.Component;

@Component
public class HandleException {

    public boolean throwCartaoExistente(){
        throw new CartaoExistenteException();
    }

}
