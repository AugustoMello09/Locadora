package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstoqueDTOInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private int quantidade;
	private StatusEstoque status;
	
	public EstoqueDTOInfo() {}
	
	public EstoqueDTOInfo(Estoque entity) {
		id = entity.getId();
		quantidade = entity.getQuantidade();
		status = entity.getStatus();
	}
}
