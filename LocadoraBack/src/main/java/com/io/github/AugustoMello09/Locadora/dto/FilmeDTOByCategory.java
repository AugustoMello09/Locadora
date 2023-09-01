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
public class FilmeDTOByCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	

	public FilmeDTOByCategory(Filme entity) {
		id = entity.getId();
		nome = entity.getNome();
	}
}
