package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDto;
import com.dbc.pessoaapi.entity.Pessoaentity;
import com.dbc.pessoaapi.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated


@RequestMapping("/pessoa")
@Slf4j

public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    public PessoaController() {

    }

    @ApiOperation(value = "Criando Pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "pessoa Criada com sucesso!"),
            @ApiResponse(code = 400, message = "pessoa com id não encontrado"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @PostMapping
    public PessoaDto create(@RequestBody@Valid PessoaCreateDTO pessoaCreateDTO) throws Exception {
        log.info("iniciando criação da pessoa");
        PessoaDto pessoaDto = pessoaService.create(pessoaCreateDTO);
        log.info("pessoa criada com sucesso");
        return pessoaDto;
    }

    @GetMapping
    @ApiOperation(value = "lista  Pessoas")
    public List<PessoaDto> list() {
        return pessoaService.list();
    }

    @ApiOperation(value = "lista todas as Pessoas pelo Nome")
    @GetMapping("/byname")
    public List<PessoaDto> listByName(@RequestParam("nome") String nome) {
        return pessoaService.listByName(nome);
    }

    @ApiOperation(value = "Atualizar uma Nova Pessoa Pelo ID Informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "pessoa atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "pessoa com id não encontrado"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @PutMapping("/{idPessoa}")
    public PessoaDto update(@PathVariable("idPessoa") Integer id,
                               @RequestBody @Valid PessoaCreateDTO PessoaDtoAtualizar) throws Exception {
        log.info("iniciando atualização da pessoa");
        PessoaDto pessoaDto =  pessoaService.update(id, PessoaDtoAtualizar);
        log.info(" pessoa atualizada");
        return pessoaDto;
    }
    @ApiOperation(value = "Deletar Pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "pessoa Deletada com sucesso!"),
            @ApiResponse(code = 400, message = "pessoa com id não encontrado"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @DeleteMapping("/{idPessoa}")
    public void delete(@PathVariable("idPessoa") Integer id) throws Exception {
        log.info(" pessoa deletada com suçesso");
        pessoaService.delete(id);
    }
}
