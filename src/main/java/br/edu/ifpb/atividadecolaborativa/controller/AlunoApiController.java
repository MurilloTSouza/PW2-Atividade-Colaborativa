package br.edu.ifpb.atividadecolaborativa.controller;

import br.edu.ifpb.atividadecolaborativa.model.Aluno;
import br.edu.ifpb.atividadecolaborativa.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("API REST Aluno")
@RestController
@RequestMapping("/api/aluno")
public class AlunoApiController {

    @Autowired
    private AlunoService service;

    @ApiOperation(value = "Retorna uma lista de alunos")
    @GetMapping(produces = "application/json")
    public @ResponseBody List<Aluno> findAll(){
        return service.findAll();
    }

    @ApiOperation(value = "Retorna um aluno a partir do seu ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Aluno findById(@PathVariable Long id){
        return service.findById(id);
    }

    @ApiOperation(value = "Cria um novo aluno e o retorna com seu novo ID")
    @PostMapping(produces = "application/json")
    public @ResponseBody Aluno save(@RequestBody @Valid Aluno aluno){
        return service.save(aluno);
    }

    @ApiOperation(value = "Deleta um aluno a partir do seu ID")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody void delete(@PathVariable Long id){
        service.delete(id);
    }

    @ApiOperation(value = "Atualiza um aluno com o ID da URL, setando os valores passados no BODY")
    @PutMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Aluno update(@PathVariable Long id, @RequestBody @Valid Aluno aluno){
        return service.update(id, aluno);
    }

}
