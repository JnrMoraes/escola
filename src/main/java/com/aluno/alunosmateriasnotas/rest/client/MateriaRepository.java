package com.aluno.alunosmateriasnotas.rest.client;

import com.aluno.alunosmateriasnotas.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository
        extends JpaRepository<Materia, Long> {
}
