package com.aluno.alunosmateriasnotas.dto;


import com.aluno.alunosmateriasnotas.entity.Materia;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class AlunoDto extends RepresentationModel<AlunoDto> {

    private Long id;

    @NotBlank(message = "Infomar o nome do Aluno")
    private String nome;

    private List<Materia> materias;


}
