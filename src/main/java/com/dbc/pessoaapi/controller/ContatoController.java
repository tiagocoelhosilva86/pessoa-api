package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.entity.Contatoentity;
import com.dbc.pessoaapi.service.ContatoService;
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
@RequestMapping("/contato")
@Validated
@Slf4j
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    public ContatoController(){
    }


    @GetMapping
    @ApiOperation(value = "Lista de contatos")
    public List<Contatoentity> list() {
        return contatoService.list();
    }

    @ApiOperation(value = "Listando contato pelo numero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " Listando contato pelo numero com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })

    @GetMapping("/bynumber")
    public List<ContatoDTO> listByNumero(@RequestParam("numero") String numero){
        log.info("Listando contato pelo numero");
        return contatoService.listByNumero(numero);
    }
    @ApiOperation(value = "Criando Contato")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "contato atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @PostMapping("/{idPessoa}")
    public ContatoDTO create(@PathVariable("idPessoa")@Valid Integer id, @RequestBody @Valid ContatoDTO contatoDTO) throws Exception {
        log.info("Criado Contato");
        ContatoDTO contatoDTO1= contatoService.create(id, contatoDTO);
        log.info("contato criado com sucesso");
        return contatoDTO1;

    }
    @ApiOperation(value = "Deletar um Contato")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "contato deletado com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @DeleteMapping("/{idContato}")
    public void delete(@PathVariable("idContato")@Valid Integer id) throws Exception {
        log.info("deletando contato");
        contatoService.delete(id);
        log.info("contato deletado com sucesso");
    }
    @ApiOperation(value = "Atualizando um  Contato por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "contato atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "voçe não tem permição para usar esse recurso"),
            @ApiResponse(code = 500, message = "Exceção no sistema!")
    })
    @PutMapping("/{idContato}")
    public ContatoDTO update(@PathVariable("idContato") @Valid Integer id, @RequestBody @Valid ContatoDTO contatoDTO) throws Exception {
        log.info(" atualizar contato");
        ContatoDTO contatoDTO1= contatoService.update(id, contatoDTO);
        log.info("contato atualizado com sucesso");
        return contatoDTO1;

    }

}
