package com.solutis.miniautorizador.controller;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CartaoDto> criar(@RequestBody @Valid CartaoDto cartaoDto){

        try {
            return new ResponseEntity<CartaoDto>(cartaoService.criar(cartaoDto),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<CartaoDto>(cartaoDto, HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Double> obterSaldo(@PathVariable(required = true) String numeroCartao){
        Optional<Double> saldo = cartaoService.obterSaldo(numeroCartao);

        return saldo.isPresent() ? new ResponseEntity<Double>(saldo.get(), HttpStatus.OK) : ResponseEntity.notFound().build();
    }

}
