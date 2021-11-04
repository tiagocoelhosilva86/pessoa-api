package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.Exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.entity.Pessoaentity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class PessoaRepository {
    private static List<Pessoaentity> listaPessoaentities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public PessoaRepository() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020
        listaPessoaentities.add(new Pessoaentity(COUNTER.incrementAndGet() /*1*/,"tiago.coelho@hotmail.com", "Maicon Gerardi", LocalDate.parse("10/10/1990", formatter), "12345678910"));
        listaPessoaentities.add(new Pessoaentity(COUNTER.incrementAndGet() /*2*/,"tiago.coelho@hotmail.com", "Charles Pereira", LocalDate.parse("08/05/1985", formatter), "12345678911"));
        listaPessoaentities.add(new Pessoaentity(COUNTER.incrementAndGet() /*3*/,"tiago.coelho@hotmail.com", "Marina Oliveira", LocalDate.parse("30/03/1970", formatter), "12345678912"));
    }

    public Pessoaentity create(Pessoaentity pessoaentity) {
        pessoaentity.setIdPessoa(COUNTER.incrementAndGet());
        listaPessoaentities.add(pessoaentity);
        return pessoaentity;
    }

    public List<Pessoaentity> list() {
        return listaPessoaentities;
    }

    public Pessoaentity update(Integer id,
                               Pessoaentity pessoaentityAtualizar) throws Exception {
        Pessoaentity pessoaentityRecuperada = listaPessoaentities.stream()
                .filter(pessoaentity -> pessoaentity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não econtrada"));
        pessoaentityRecuperada.setCpf(pessoaentityAtualizar.getCpf());
        pessoaentityRecuperada.setNome(pessoaentityAtualizar.getNome());
        pessoaentityRecuperada.setDataNascimento(pessoaentityAtualizar.getDataNascimento());
        return pessoaentityRecuperada;
    }

    public Pessoaentity delete(Integer id) throws Exception {
        Pessoaentity pessoaentityRecuperada = listaPessoaentities.stream()
                .filter(pessoaentity -> pessoaentity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
        listaPessoaentities.remove(pessoaentityRecuperada);
        return pessoaentityRecuperada;
    }

    public List<Pessoaentity> listByName(String nome) {
        return listaPessoaentities.stream()
                .filter(pessoaentity -> pessoaentity.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
}
