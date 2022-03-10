package com.solutis.miniautorizador.dto;

public class EnderecoDto extends EnderecoCriacaoDto {

    private Integer id;

    public EnderecoDto(String logradouro, String cidade, String estado, String complemento, Integer id) {
        super(logradouro, cidade, estado, complemento);
        this.id = id;
    }

}
