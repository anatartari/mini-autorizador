package com.solutis.miniautorizador.controller;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService servicoCartao;

    @PostMapping
    public ResponseEntity<CartaoDto> criar(@RequestBody @Valid CartaoDto cartaoDto){

        try {
            return new ResponseEntity<CartaoDto>(servicoCartao.criar(cartaoDto),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<CartaoDto>(cartaoDto, HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }


}
