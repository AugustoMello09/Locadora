package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.io.github.AugustoMello09.Locadora.entity.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo Obrigatório")
	private String name;

	public EstadoDTO() {
	}

	public EstadoDTO(Estado entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

}
