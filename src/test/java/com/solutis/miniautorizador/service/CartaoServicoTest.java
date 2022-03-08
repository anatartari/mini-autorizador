package com.solutis.miniautorizador.service;

import com.solutis.miniautorizador.dto.TransacaoDto;
import com.solutis.miniautorizador.exception.CartaoExistenteException;
import com.solutis.miniautorizador.exception.CartaoInexistenteException;
import com.solutis.miniautorizador.exception.SaldoInsuficienteException;
import com.solutis.miniautorizador.exception.SenhaIncorretaException;
import com.solutis.miniautorizador.model.Cartao;
import com.solutis.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import com.solutis.miniautorizador.dto.CartaoDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartaoServicoTest {

    @Autowired(required = true)
    private CartaoService servicoDeCartao;
    @Autowired
    private CartaoRepository cartaoRepository;

    private void Inicializar(){
        Cartao cartao = new Cartao("123456789", "123456", 700.00);
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
        Inicializar();
        CartaoDto cartaoDuplicado = new CartaoDto("123456789", "senha");

        try{
            CartaoDto cartaoCriado = servicoDeCartao.criar(cartaoDuplicado);
            fail();
        }
        catch(Exception e){
            assertTrue(e.getClass() == CartaoExistenteException.class);
        }
    }

    @Test
    public void deveReotornarSaldoDoCartaoCadastradoParaNumeroExistente(){
        Inicializar();
        String numeroDeCartaoExistente = "123456789";

        Optional<Double> saldoCartao = servicoDeCartao.obterSaldo(numeroDeCartaoExistente);

        assertEquals(700.00, saldoCartao.get());
    }

    @Test
    public void deveReotornarErroParaNumeroInexistente(){
        Inicializar();
        String numeroDeCartaoInexistente = "111111111";

        Optional<Double> saldoCartao = servicoDeCartao.obterSaldo(numeroDeCartaoInexistente);

        assertFalse(saldoCartao.isPresent());

    }

    @Test
    public void deveRetornarTransacaoBemSucedida(){
        Inicializar();
        Double saldoCartao = cartaoRepository.findById("123456789").get().getSaldo();
        TransacaoDto transacao = new TransacaoDto("123456789", "123456", 100.00);

        String resultado = servicoDeCartao.realizarTransacao(transacao);

        Optional<Cartao> cartaoAtualziado = cartaoRepository.findById("123456789");

        assertEquals("OK", resultado);
        assertTrue(cartaoAtualziado.get().getSaldo() == (saldoCartao - transacao.getValor()));
    }

    @Test
    public void deveRetornarErroParaTransacaoUsandoCartaoInexistente(){

        TransacaoDto transacao = new TransacaoDto("123", "123456", 100.00);

        try {
            String resultado = servicoDeCartao.realizarTransacao(transacao);
            fail();
        }catch (Exception e){
            assertSame(e.getClass(), CartaoInexistenteException.class);
        }
    }

    @Test
    public void deveRetornarErroParaTransacaoUsandoSenhaIncorreta(){
        Inicializar();
        TransacaoDto transacao = new TransacaoDto("123456789", "00000", 100.00);

        try {
            String resultado = servicoDeCartao.realizarTransacao(transacao);
            fail();
        }catch (Exception e){
            assertSame(e.getClass(), SenhaIncorretaException.class);
        }
    }

    @Test
    public void deveRetornarErroParaTransacaoUsandoCartaoComSaldoInsuficiente(){
        Inicializar();
        TransacaoDto transacao = new TransacaoDto("123456789", "123456", 100000.00);

        try {
            String resultado = servicoDeCartao.realizarTransacao(transacao);
            fail();
        }catch (Exception e){
            assertSame(e.getClass(), SaldoInsuficienteException.class);
        }
    }

}
