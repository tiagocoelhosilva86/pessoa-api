package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.Exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDto;
import com.dbc.pessoaapi.entity.Pessoaentity;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EmailService emailService;
    private final ObjectMapper objectMapper;



    public PessoaDto create(PessoaCreateDTO pessoaCreateDTO) throws Exception {

        Pessoaentity pessoaentity = objectMapper.convertValue(pessoaCreateDTO, Pessoaentity.class);
        Pessoaentity pessoaCriada = pessoaRepository.create(pessoaentity);

        emailService.enviarEmailComTemplate(pessoaCriada.getEmail(),pessoaCriada.getIdPessoa(),pessoaCriada.getNome());

        PessoaDto pessoaDTO = objectMapper.convertValue(pessoaCriada, PessoaDto.class);
        return pessoaDTO;


    }
    public List<PessoaDto> list() {
        return pessoaRepository.list().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa,PessoaDto.class))
                .collect(Collectors.toList());
    }

    public PessoaDto update(Integer id,
                            PessoaCreateDTO pessoaCreateDTO) throws Exception {
        Pessoaentity entity = objectMapper.convertValue(pessoaCreateDTO, Pessoaentity.class);
        Pessoaentity update = pessoaRepository.update(id, entity);
        PessoaDto dto = objectMapper.convertValue(update, PessoaDto.class);
        return dto;
    }

    public void delete(Integer id) throws Exception {
         Pessoaentity pessoaentity = pessoaRepository.delete(id);
         emailService.enviaremailcomtemplatepessoaexcluida(pessoaentity.getEmail(), pessoaentity.getNome());

    }

    public List<PessoaDto> listByName(String nome) {
        return pessoaRepository.listByName(nome).stream()
                .map(pessoa -> objectMapper.convertValue(pessoa,PessoaDto.class))
                .collect(Collectors.toList());
    }
}
