package com.aluno.alunosmateriasnotas.repositories;

import com.aluno.alunosmateriasnotas.model.entities.Materia;
import org.springframework.data.repository.CrudRepository;

public interface MateriaRepository
        extends CrudRepository<Materia, Long> {
}
