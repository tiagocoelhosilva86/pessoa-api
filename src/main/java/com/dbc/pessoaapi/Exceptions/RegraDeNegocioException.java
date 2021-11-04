package com.dbc.pessoaapi.Exceptions;

public class RegraDeNegocioException extends Exception {
    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }
}