package com.aluno.alunosmateriasnotas.repositories;

import com.aluno.alunosmateriasnotas.model.entities.Nota;
import org.springframework.data.repository.CrudRepository;

public interface NotaRepository
        extends CrudRepository<Nota, Long> {
}
