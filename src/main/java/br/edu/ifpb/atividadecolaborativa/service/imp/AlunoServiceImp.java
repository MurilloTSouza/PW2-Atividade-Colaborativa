package br.edu.ifpb.atividadecolaborativa.service.imp;

import br.edu.ifpb.atividadecolaborativa.model.Aluno;
import br.edu.ifpb.atividadecolaborativa.repository.AlunoRepository;
import br.edu.ifpb.atividadecolaborativa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImp implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public List<Aluno> findAll() {
        return repository.findAll();
    }

    @Override
    public Aluno findById(Long id) {
        Optional<Aluno> aluno = repository.findById(id);
        if(aluno.isPresent()) { return repository.findById(id).get(); }
        else { return null; }
    }

    @Override
    public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Aluno update(Long id, Aluno aluno) {
        Aluno original = findById(id);
        if(original == null){ return null; }

        aluno.setId(id);
        return repository.save(aluno);
    }
}
