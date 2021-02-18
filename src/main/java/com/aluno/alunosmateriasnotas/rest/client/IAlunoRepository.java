package com.aluno.alunosmateriasnotas.rest.client;

import com.aluno.alunosmateriasnotas.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlunoRepository
        extends JpaRepository<Aluno, Long> {


}
