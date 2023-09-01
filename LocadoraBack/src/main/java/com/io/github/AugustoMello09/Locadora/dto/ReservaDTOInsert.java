package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Reserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservaDTOInsert extends ReservaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserDTO user;
	private EstoqueDTO estoque;
	
	public ReservaDTOInsert() {}
	
	public ReservaDTOInsert(Reserva entity) {
		user = new UserDTO(entity.getUser());
		estoque = new EstoqueDTO(entity.getEstoque());
	}

}		
