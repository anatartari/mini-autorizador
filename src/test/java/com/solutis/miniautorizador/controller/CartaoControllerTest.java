package com.solutis.miniautorizador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutis.miniautorizador.dto.CartaoCriacaoDto;
import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.service.CartaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CartaoController.class)
@ContextConfiguration(classes = CartaoController.class)
public class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartaoService cartaoService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deveRetornar200SeSaldoConsultadoComSucesso() throws Exception {
        String uri = "/cartoes/123456789";
        when(cartaoService.obterSaldo(anyString())).thenReturn(Optional.of(500.00));
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarBadRequestSeBuscarSaldoDeCartaoInvalido() throws Exception {
        String uri = "/cartoes/1234";
        when(cartaoService.obterSaldo(anyString())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.ALL))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveRetornar201SeCartaoCriadoComSucesso() throws Exception {
        String uri = "/cartoes";
        CartaoDto dto = new CartaoDto("numero", "senha", "01/01/2020", "01/01/2023", "123456789");
        when(cartaoService.criar(Mockito.mock(CartaoCriacaoDto.class))).thenReturn(dto);
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveRetornar400SeInformacoesDoCartaoIncorretas() throws Exception {
        String uri = "/cartoes";
        CartaoDto dto = new CartaoDto("", "", "01/01/2020", "01/01/2023", "123456789");
        when(cartaoService.criar(Mockito.mock(CartaoCriacaoDto.class))).thenReturn(dto);
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
