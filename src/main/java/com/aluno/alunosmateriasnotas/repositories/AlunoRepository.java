package com.aluno.alunosmateriasnotas.repositories;

import com.aluno.alunosmateriasnotas.model.entities.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository
        extends CrudRepository<Aluno, Long> {
}
