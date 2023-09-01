package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(max = 20, message = "Deve ter no máximo a 20 caracteres")
	private String nomeCategoria;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nomeCategoria = entity.getNomeCategoria();
	}
}
