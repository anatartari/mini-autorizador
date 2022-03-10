package com.solutis.miniautorizador.model;

import com.solutis.miniautorizador.dto.CartaoDto;
import com.solutis.miniautorizador.dto.ClienteCriacaoDto;
import com.solutis.miniautorizador.dto.EnderecoDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String cpf;
    public LocalDate data_inicio;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cartao> cartoes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(ClienteCriacaoDto clienteRequest) {
        this.cpf = clienteRequest.getCpf();
        this.data_inicio = LocalDate.now();
        this.endereco = new Endereco(clienteRequest.getEndereco());
    }
}
