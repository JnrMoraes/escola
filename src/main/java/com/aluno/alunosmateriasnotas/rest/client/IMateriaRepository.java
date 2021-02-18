package com.aluno.alunosmateriasnotas.rest.client;

import com.aluno.alunosmateriasnotas.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMateriaRepository
        extends JpaRepository<Materia, Long> {
}
