package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    private Integer idPessoa;

    @NotEmpty
    @NotBlank
    @ApiModelProperty(value = "Nome da Pessoa")
    private String nome;


    @NotNull
    @Past
    @ApiModelProperty(value = "Data de Nascimento")
    private LocalDate dataNascimento;
    @NotBlank
    @NotEmpty
    @Size(max = 11, min = 11, message = "cpf deve conter 11 caracteres")
    @ApiModelProperty(value = "CPF")
    private String cpf;
}
