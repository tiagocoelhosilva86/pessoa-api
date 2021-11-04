package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
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
public class EnderecoCreateDto {
    @NotNull
    private TipoEndereco tipo;
    @NotEmpty
    @NotBlank
    @Size(max=250)
    @ApiModelProperty(value = "logradouro")
    private String logradouro;
    @NotNull
    @ApiModelProperty(value = "Numero")
    private Integer numero;
    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Complemento")
    private String complemento;
    @NotEmpty
    @NotBlank
    @Size(max = 8)
    @ApiModelProperty(value = "Cep")
    private String cep;
    @NotNull
    @NotEmpty
    @Size(max = 250)
    @ApiModelProperty(value = "Cidade")
    private String cidade;
    @NotNull
    @ApiModelProperty(value = "Estado")
    private String estado;
    @NotNull
    @ApiModelProperty(value = "Pais")
    private String pais;
}
