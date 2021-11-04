package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.Exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.entity.Enderecoentity;
import com.dbc.pessoaapi.entity.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<Enderecoentity> enderecoentityList = new ArrayList<Enderecoentity>();
    private AtomicInteger COUNTERPESSOA = new AtomicInteger();
    private AtomicInteger COUNTERPESSOAENDERECO = new AtomicInteger();


    public EnderecoRepository() {

        enderecoentityList.add(new Enderecoentity(COUNTERPESSOAENDERECO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(),TipoEndereco.RESIDENCIAL,"logradouro",4,"proximo ão mercado","54705-200","são lourenço da mata","Pernanbuco","Brasil"));

    }
    public Enderecoentity create(Integer idPessoa, Enderecoentity enderecoentity) {
        enderecoentity.setIdPessoa(idPessoa);
        enderecoentity.setId(COUNTERPESSOAENDERECO.incrementAndGet());
        enderecoentityList.add(enderecoentity);
        return enderecoentity;
    }
    public Enderecoentity update(Integer id, Enderecoentity enderecoentity) throws Exception {
        Enderecoentity enderecoentity1Alterado = enderecoentityList.stream()
                .filter(x -> x.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não econtrada"));
        enderecoentity1Alterado.setTipo(enderecoentity.getTipo());
        enderecoentity1Alterado.setCep(enderecoentity.getCep());
        enderecoentity1Alterado.setNumero(enderecoentity.getNumero());
        enderecoentity1Alterado.setCidade(enderecoentity.getCidade());
        enderecoentity1Alterado.setComplemento(enderecoentity.getComplemento());
        enderecoentity1Alterado.setEstado(enderecoentity.getEstado());
        enderecoentity1Alterado.setPais(enderecoentity.getPais());
        enderecoentity1Alterado.setComplemento(enderecoentity.getComplemento());
        enderecoentity1Alterado.setLogradouro(enderecoentity.getLogradouro());

        return enderecoentity1Alterado;
    }
    public List<Enderecoentity> list() {
        return enderecoentityList;
    }
    public List<Enderecoentity> listById(Integer id) {
        return enderecoentityList.stream()
                .filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Enderecoentity> listByIdPessoa(Integer idPessoa) {
        return enderecoentityList.stream()
                .filter(x -> x.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }
    public Enderecoentity listByIdpessoa(Integer idpessoa) throws Exception {
        return enderecoentityList.stream()
                .filter(x -> x.getIdPessoa().equals(idpessoa))
                .findFirst()
                .orElseThrow(() -> new Exception("endereco não encontrado"));
    }
    public void delete(Integer id) throws Exception {
        Enderecoentity enderecoentityRecuperado = enderecoentityList.stream()
                .filter(pessoa -> pessoa.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("endereço não econtrada"));
        enderecoentityList.remove(enderecoentityRecuperado);
    }


}
