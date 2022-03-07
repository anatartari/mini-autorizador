package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.exception.CartaoExistenteException;
import com.solutis.miniautorizador.model.Cartao;
import com.solutis.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import com.solutis.miniautorizador.dto.CartaoDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartaoServicoTest {

    @Autowired(required = true)
    private CartaoService servicoDeCartao;
    @Autowired
    private CartaoRepository cartaoRepository;

    @BeforeAll
    private void Inicializar(){
        Cartao cartao = new Cartao("123456789", "123456", 500);
        cartaoRepository.save(cartao);
    }

    @Test
    public void deveRetornarSucessoAoCriarCartao(){
        CartaoDto cartao = new CartaoDto("987654321", "123456");

        CartaoDto cartaoCriado = servicoDeCartao.criar(cartao);

        assertEquals(500.00, cartaoRepository.findById(cartaoCriado.getNumeroCartao()).get().getSaldo());
    }

    @Test
    public void deveRetornarErroAoCriarCartaoComNumeroExistente(){

        Cartao cartao = new Cartao("123456789", "123456", 500);
        cartaoRepository.save(cartao);

        CartaoDto cartaoDuplicado = new CartaoDto("123456789", "senha");

        try{
            CartaoDto cartaoCriado = servicoDeCartao.criar(cartaoDuplicado);
            fail();
        }
        catch(Exception e){
            assertTrue(e.getClass() == CartaoExistenteException.class);
        }

    }
}
