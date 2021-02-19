package com.aluno.alunosmateriasnotas.dto;

import com.aluno.alunosmateriasnotas.entity.Nota;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MateriaDto {

    private Long id;

    @NotBlank(message = "Informar nome da Matéria")
    private String nome;

    private List<Nota> nota;
}
