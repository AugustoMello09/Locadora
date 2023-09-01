package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private int quantidade;
	private StatusEstoque status;

	private List<ReservaDTO> reservas = new ArrayList<>();
	private List<ReservaOnlineDTO> reservasOnline = new ArrayList<>();

	private int quantidadeFilmesReservados;
	private int quantidadeFilmesReservadosOnline;
	private int quantidadeFilmesDisponiveis;

	public EstoqueDTO() {
	}

	public EstoqueDTO(Estoque entity) {
		this.id = entity.getId();
		this.quantidade = entity.getQuantidade();
		this.status = entity.getStatus();
		this.quantidadeFilmesReservados = entity.getQuantidadeFilmesReservados();
		this.quantidadeFilmesReservadosOnline = entity.getQuantidadeFilmesReservadosOnline();
		this.quantidadeFilmesDisponiveis = entity.getQuantidadeFilmesDisponiveis();
		entity.getReservas().forEach(x -> this.reservas.add(new ReservaDTO(x)));
		entity.getReservasOnline().forEach(x -> this.reservasOnline.add(new ReservaOnlineDTO(x)));
	}

}
