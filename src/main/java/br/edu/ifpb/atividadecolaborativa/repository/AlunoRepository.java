package br.edu.ifpb.atividadecolaborativa.repository;

import br.edu.ifpb.atividadecolaborativa.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
