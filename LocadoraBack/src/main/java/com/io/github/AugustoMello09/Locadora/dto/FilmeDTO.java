package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilmeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 2, max = 24, message = "Deve ter entre 2 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@Size(min = 2, max = 250, message = "Deve ter entre 2 a 1000 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String descricao;
	
	@Size(min = 2, max = 24, message = "Deve ter entre 2 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String diretor;
	
	@Positive
	private Double valorAluguel;

	private CategoriaDTO categoria;
	private EstoqueDTO estoque;
	
	public FilmeDTO() {}
	
	public FilmeDTO(Filme entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();
		diretor = entity.getDiretor();
		valorAluguel = entity.getValorAluguel();
		categoria = new CategoriaDTO(entity.getCategoria());
		estoque = new EstoqueDTO(entity.getEstoque());
	}
}
