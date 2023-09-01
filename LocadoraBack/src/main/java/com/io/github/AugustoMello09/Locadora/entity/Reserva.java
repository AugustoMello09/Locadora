package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_reserva")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int qtdReservada;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataReserva;

	private Integer statusReserva;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "estoque_id", referencedColumnName = "id")
	private Estoque estoque;

	public Reserva(Long id, int qtdReservada, LocalDate dataReserva, User user, Estoque estoque,
			StatusReserva statusReserva) {
		super();
		this.id = id;
		this.qtdReservada = qtdReservada;
		this.dataReserva = dataReserva;
		this.user = user;
		this.estoque = estoque;
		this.statusReserva = (statusReserva == null) ? 0 : statusReserva.getCod();
	}

	public StatusReserva getStatus() {
		return StatusReserva.toEnum(this.statusReserva);
	}

	public void setStatus(StatusReserva status) {
		this.statusReserva = status.getCod();
	}

}