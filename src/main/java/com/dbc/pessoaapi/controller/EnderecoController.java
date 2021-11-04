package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.EnderecoCreateDto;
import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.entity.Enderecoentity;
import com.dbc.pessoaapi.service.EnderecoService;
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
@RequestMapping("/Endereco")
@Validated
@Slf4j
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Criando Endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço Criado com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @PostMapping("/{idPessoa}")
    public EnderecoDTO create(@PathVariable("idPessoa") @Valid Integer idPessoa, @RequestBody  @Valid EnderecoDTO enderecoDTO) throws  Exception {
        log.info(" criado ");
        EnderecoDTO enderecoDTO1 =enderecoService.create(idPessoa, enderecoDTO);
        log.info("endereço criado com sucesso");
        return enderecoDTO1;
    }

    @GetMapping
    @ApiOperation(value = "Lista de Endereço")
    public List<EnderecoDTO> List(){return enderecoService.list();}

    @GetMapping("/{idPessoa}/pessoa")
    @ApiOperation(value = "Lista de Endereço Pelo id da Pessoa")
    public List<EnderecoDTO> listByPessoa(@PathVariable("idPessoa")Integer idPessoa){
        return enderecoService.listByIdPessoa(idPessoa);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista de Endereço por id")
    public List<EnderecoDTO> listById(@PathVariable("id")Integer id){
        return enderecoService.listById(id);
    }


    @ApiOperation(value = "Atualizando Endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço  atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @PutMapping("/{id}")
    public EnderecoDTO update(@PathVariable("id")@Valid Integer id,
                                 @RequestBody @Valid EnderecoCreateDto enderecoCreateDto) throws Exception {
        log.info("atualizando endereço");
        EnderecoDTO enderecoDTO1 = enderecoService.update(id, enderecoCreateDto);
        log.info("endereço atualizado");
        return enderecoDTO1;
    }

    @ApiOperation(value = "Deletando Endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço Deletado com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") @Valid Integer id) throws Exception {
        log.info("deletando endereço");
        enderecoService.delete(id);
        log.info("endereço deletado");

    }

}
