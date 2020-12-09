package br.edu.ifpb.atividadecolaborativa.controller;

import br.edu.ifpb.atividadecolaborativa.model.Aluno;
import br.edu.ifpb.atividadecolaborativa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoApiController {

    @Autowired
    private AlunoService service;

    @GetMapping(produces = "application/json")
    public @ResponseBody List<Aluno> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Aluno findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping(produces = "application/json")
    public @ResponseBody Aluno save(@RequestBody @Valid Aluno aluno){
        return service.save(aluno);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Aluno update(@PathVariable Long id, @RequestBody @Valid Aluno aluno){
        return service.update(id, aluno);
    }

}
