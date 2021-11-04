package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.dto.PessoaDto;
import com.dbc.pessoaapi.entity.Contatoentity;
import com.dbc.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;
    private final ObjectMapper objectMapper;


    public ContatoDTO create(Integer id,ContatoDTO contatoDTO) throws Exception {

        Contatoentity contatoentity = objectMapper.convertValue(contatoDTO, Contatoentity.class);
        Contatoentity contatoCriada = contatoRepository.create(id, contatoentity);

        ContatoDTO contatoDTO1 = objectMapper.convertValue(contatoCriada, ContatoDTO.class);
        return contatoDTO1;


    }


    public List<Contatoentity> list(){
        return contatoRepository.list();
    }

    public ContatoDTO update(Integer id, ContatoDTO contatoDTO) throws Exception {
        Contatoentity contatoentity = objectMapper.convertValue(contatoDTO, Contatoentity.class);
        Contatoentity contatoentity1= contatoRepository.update(id, contatoentity);

        ContatoDTO contatoDTO1 = objectMapper.convertValue(contatoentity1, ContatoDTO.class);
        return contatoDTO1;

    }

    public void delete(Integer id) throws Exception {
        contatoRepository.delete(id);
    }

    public List<ContatoDTO> listByNumero(String numero) {
        return contatoRepository.listByNumero(numero).stream()
                .map(contato -> objectMapper.convertValue(contato,ContatoDTO.class))
                .collect(Collectors.toList());
    }

}
