package com.dbc.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enderecoentity {
    private Integer id;
    private Integer idPessoa;
    @NotNull
    private TipoEndereco tipo;
    @NotEmpty
    @NotBlank
    @Size(max=250)
    private String logradouro;
    @NotNull
    private Integer numero;
    @NotEmpty
    @NotBlank
    private String complemento;
    @NotEmpty
    @NotBlank
    @Size(max = 8)
    private String cep;
    @NotNull
    @NotEmpty
    @Size(max = 250)
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String pais;


}

