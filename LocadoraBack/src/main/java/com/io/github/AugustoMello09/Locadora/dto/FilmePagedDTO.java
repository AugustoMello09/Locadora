package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmePagedDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private String diretor;
	private Double valor;
	private int quantidadeDisponivel;
	private int quantidadeReservadas;
	private int quantidadeReservadasOnline;

	public FilmePagedDTO(Filme entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();
		diretor = entity.getDiretor();
		valor = entity.getValorAluguel();
		quantidadeDisponivel = entity.getEstoque().getQuantidadeFilmesDisponiveis();
		quantidadeReservadas = entity.getEstoque().getQuantidadeFilmesReservados();
		quantidadeReservadasOnline = entity.getEstoque().getQuantidadeFilmesReservadosOnline();
	}
}
