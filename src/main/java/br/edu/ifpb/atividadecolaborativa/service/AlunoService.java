package br.edu.ifpb.atividadecolaborativa.service;

import br.edu.ifpb.atividadecolaborativa.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AlunoService {
    List<Aluno> findAll();
    Aluno findById(Long id);
    Aluno save(Aluno aluno);
    void delete(Long id);
    Aluno update(Long id, Aluno aluno);
}
