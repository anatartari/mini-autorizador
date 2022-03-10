package com.solutis.miniautorizador.controller;

import com.solutis.miniautorizador.dto.CartaoCriacaoDto;
import com.solutis.miniautorizador.dto.ClienteCriacaoDto;
import com.solutis.miniautorizador.dto.ClienteDto;
import com.solutis.miniautorizador.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<Object> criar(@RequestBody ClienteCriacaoDto clienteCriacaoDto){

        try {
            return new ResponseEntity<Object>(clienteService.criar(clienteCriacaoDto), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<Object>(clienteCriacaoDto, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
