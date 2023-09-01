package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReservaOnlineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "campo obrigatório")
	@Min(value = 1, message = "O mínimo para reserva é 1")
	private int qtdReservada;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataReserva;
	
	private StatusReserva statusReserva;
	
	public ReservaOnlineDTO() {}
	
	public ReservaOnlineDTO(ReservaOnline entity) {
		this.id = entity.getId();
        this.qtdReservada = entity.getQtdReservada();
        this.dataReserva = entity.getDataReserva();
        this.statusReserva = entity.getStatus();  
	}

}		
