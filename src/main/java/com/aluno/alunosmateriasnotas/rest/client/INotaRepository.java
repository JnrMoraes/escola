package com.aluno.alunosmateriasnotas.rest.client;

import com.aluno.alunosmateriasnotas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotaRepository extends JpaRepository<Nota, Long> {
}
