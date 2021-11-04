package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaCreateDTO {
        private Integer idPessoa;

        @NotEmpty
        @NotBlank
        @ApiModelProperty(value = "email")
        private String email;

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


