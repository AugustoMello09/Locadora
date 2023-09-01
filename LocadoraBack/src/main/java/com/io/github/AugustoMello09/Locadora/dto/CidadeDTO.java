package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.Cidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 4, max = 24, message = "Deve ter entre 4 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	private EstadoDTO estadoId;
	
	public CidadeDTO() {}

	public CidadeDTO(Cidade entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.estadoId = new EstadoDTO(entity.getEstado());
	}
}
