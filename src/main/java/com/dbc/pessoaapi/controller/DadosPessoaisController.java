package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDto;
import com.dbc.pessoaapi.service.DadosPessoaisService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated


@RequestMapping("/dadospessoais")
@Slf4j
@RequiredArgsConstructor
public class DadosPessoaisController {
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    @PostMapping
    public DadosPessoaisDTO create(@RequestBody @Valid DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
        log.info("iniciando criação da pessoa");
        DadosPessoaisDTO dadosPessoaisDTO1 = dadosPessoaisService.create(dadosPessoaisDTO);
        log.info("pessoa criada com sucesso");
        return dadosPessoaisDTO1;
    }

    @GetMapping
    public List<DadosPessoaisDTO> list() {
        return dadosPessoaisService.list();
    }




}
