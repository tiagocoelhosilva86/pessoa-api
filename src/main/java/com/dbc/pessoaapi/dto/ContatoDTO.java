package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class ContatoDTO {

    private Integer idContato;
    private Integer idPessoa;

    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Comercial,Residencial")
    private String tipoContato;

    @NotEmpty
    @NotBlank
    @Size(max = 13)
    @ApiModelProperty(value = "Numero")
    private String numero;

    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Descrição")
    private String descricao;
}
