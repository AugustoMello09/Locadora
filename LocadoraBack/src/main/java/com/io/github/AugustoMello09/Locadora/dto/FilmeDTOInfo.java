package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilmeDTOInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private EstoqueDTOInfo estoque;
	
	public FilmeDTOInfo() {}
	
	public FilmeDTOInfo(Filme entity) {
		id = entity.getId();
		nome = entity.getNome();
		estoque = new EstoqueDTOInfo(entity.getEstoque());
	}
}
