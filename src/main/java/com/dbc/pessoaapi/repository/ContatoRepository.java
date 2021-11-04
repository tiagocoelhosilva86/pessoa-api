package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.Exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.entity.Contatoentity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Repository
public class ContatoRepository {
    private static List<Contatoentity> listaContato = new ArrayList<Contatoentity>();
    private AtomicInteger COUNTERPESSOA = new AtomicInteger();
    private AtomicInteger COUNTERCONTATO = new AtomicInteger();

    public ContatoRepository() {
        listaContato.add(new Contatoentity(COUNTERCONTATO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(), "1", "1", "Tiago"));
        listaContato.add(new Contatoentity(COUNTERCONTATO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(), "2", "2", "Diego"));
        listaContato.add(new Contatoentity(COUNTERCONTATO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(), "3", "3", "Hugo"));
    }


    public Contatoentity create(Integer id, Contatoentity contatoentity) {
        contatoentity.setIdContato(COUNTERCONTATO.incrementAndGet());
        contatoentity.setIdPessoa(id);
        listaContato.add(contatoentity);

        return contatoentity;
    }

    public List<Contatoentity> list() {
        return listaContato;
    }

    public Contatoentity update(Integer id, Contatoentity contatoAtualizar) throws Exception {
        Contatoentity contatoRecuperado = listaContato.stream()
                .filter(contatoentity -> contatoentity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));
        contatoRecuperado.setIdContato(contatoAtualizar.getIdPessoa());
        contatoRecuperado.setIdContato(id);
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        return contatoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Contatoentity contatoRecuperada = listaContato.stream()
                .filter(contatoentity -> contatoentity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        listaContato.remove(contatoRecuperada);
    }

    public List<Contatoentity> listByNumero(String numero) {
        return listaContato.stream()
                .filter(contatoentity -> contatoentity.getNumero().toUpperCase().contains(numero.toUpperCase()))
                .collect(Collectors.toList());
    }
}
