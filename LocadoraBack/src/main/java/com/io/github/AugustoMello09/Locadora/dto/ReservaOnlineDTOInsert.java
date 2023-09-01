package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservaOnlineDTOInsert extends ReservaOnlineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserDTO user;
	private EstoqueDTO estoque;
	
	public ReservaOnlineDTOInsert() {}
	
	public ReservaOnlineDTOInsert(ReservaOnline entity) {
		user = new UserDTO(entity.getUser());
		estoque = new EstoqueDTO(entity.getEstoque());
	}

}		
