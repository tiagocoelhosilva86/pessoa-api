package com.dbc.pessoaapi.entity;

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
public class Pessoaentity {
    private Integer idPessoa;

    @NotEmpty
    @NotBlank
    private String email;

    @NotEmpty
    @NotBlank
    private String nome;


    @NotNull
    @Past
    private LocalDate dataNascimento;
    @NotBlank
    @NotEmpty
    @Size(max = 11, min = 11)
    private String cpf;


    @Override
    public String toString() {
        return "Pessoa{" +
                "idPessoa=" + idPessoa +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}