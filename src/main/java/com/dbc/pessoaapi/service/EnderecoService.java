package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.EnderecoCreateDto;
import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDto;
import com.dbc.pessoaapi.entity.Enderecoentity;
import com.dbc.pessoaapi.entity.Pessoaentity;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;


    public EnderecoDTO create(Integer idPessoa, EnderecoDTO enderecoDTO) throws Exception {

        Enderecoentity enderecoentity =  objectMapper.convertValue(enderecoDTO,Enderecoentity.class);
        Enderecoentity enderecoentity1  = enderecoRepository.create(idPessoa, enderecoentity);

        EnderecoDTO enderecoDTO1 = objectMapper.convertValue(enderecoentity1, EnderecoDTO.class);

        return enderecoDTO1;


   // public Enderecoentity create(Integer idPessoa, Enderecoentity enderecoentity) throws Exception {
     //   return enderecoRepository.create(idPessoa, enderecoentity);

    }
    public List<EnderecoDTO> list() {
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco,EnderecoDTO.class))
                .collect(Collectors.toList());
    }
    public EnderecoDTO update(Integer id,
                                 EnderecoCreateDto enderecoCreateDto) throws Exception {
        Enderecoentity enderecoentity =  objectMapper.convertValue(enderecoCreateDto,Enderecoentity.class);
       Enderecoentity enderecoentity1 = enderecoRepository.update(id, enderecoentity);

        EnderecoDTO enderecoDTO1 = objectMapper.convertValue(enderecoentity1, EnderecoDTO.class);
        return enderecoDTO1;
    }
    public void delete(Integer id) throws Exception {
        enderecoRepository.delete(id);
    }
    public List<EnderecoDTO> listByIdPessoa(Integer id) {
        return enderecoRepository.listByIdPessoa(id).stream()
                .map(endereco -> objectMapper.convertValue(endereco,EnderecoDTO.class))
                .collect(Collectors.toList());
    }
    public List<EnderecoDTO> listById(Integer id) {
        return enderecoRepository.listById(id).stream()
                .map(endereco -> objectMapper.convertValue(endereco,EnderecoDTO.class))
                .collect(Collectors.toList());
    }

}



