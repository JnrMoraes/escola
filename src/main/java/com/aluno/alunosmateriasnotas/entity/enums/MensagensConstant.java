package com.aluno.alunosmateriasnotas.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensConstant {
	
	ERRO_GENERICO("Erro interno identificado."),

	ERRO_MATERIA_NAO_ENCONTRADA("Matéria não encontrada."),

	ERRO_ALUNO_NAO_ENCONTRADA("Aluno não encontrada."),
	ERRO_ID_ALUNO_INFORMADO("Cadastro esta com id informado."),
	ERRO_ALUNO_ENCONTRADO("Aluno já consta em base de dados."),

	ERRO_NOTA_NAO_ENCONTRADA("Nota não encontrada."),

	ERRO_ID_INFORMADO("ID não pode ser informado na operação de cadastro."),

	 DELETE("DELETE"),

	 UDPATE ("UPDATE");

	private final String valor;
	
}
