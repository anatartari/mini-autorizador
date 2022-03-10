package com.solutis.miniautorizador.model;

import com.solutis.miniautorizador.dto.EnderecoCriacaoDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String cidade;
    private String estado;
    private String complemento;

    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(EnderecoCriacaoDto endereco) {
        this.logradouro = endereco.getLogradouro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.complemento = endereco.getComplemento();
    }
}
