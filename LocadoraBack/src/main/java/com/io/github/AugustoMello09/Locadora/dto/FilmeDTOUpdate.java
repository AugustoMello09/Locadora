package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilmeDTOUpdate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(max = 30, message = "Deve ter no máximo até 30 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@Size(min = 2, max = 1000, message = "Deve ter entre 2 a 1000 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String descricao;
	
	@Size(max = 24, message = "Deve ter no máximo até 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String diretor;

	public FilmeDTOUpdate() {
	}

	public FilmeDTOUpdate(Filme entity) {
		this.nome = entity.getNome();
		this.descricao = entity.getDescricao();
		this.diretor = entity.getDiretor();
	}
}
