package com.aluno.alunosmateriasnotas.rest;

import com.aluno.alunosmateriasnotas.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository
        extends JpaRepository<Aluno, Long> {


}
