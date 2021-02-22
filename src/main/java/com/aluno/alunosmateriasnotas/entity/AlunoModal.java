package com.aluno.alunosmateriasnotas.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlunoModal {

    private Long id;

    private List<Long> materias;

}
