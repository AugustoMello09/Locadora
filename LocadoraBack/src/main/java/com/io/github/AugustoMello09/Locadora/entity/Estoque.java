package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_estoque")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int quantidade;

	private Integer status;

	@OneToMany(mappedBy = "estoque")
	private List<Filme> filmes = new ArrayList<>();

	@OneToMany(mappedBy = "estoque")
	private List<Reserva> reservas = new ArrayList<>();

	@OneToMany(mappedBy = "estoque")
	private List<ReservaOnline> reservasOnline = new ArrayList<>();

	public Estoque(Long id, int quantidade, StatusEstoque status) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.status = status.getCod();
	}

	public void setQtd(int quantidade) {
		this.quantidade = quantidade;
		int quantidadeDisponivel = this.getQuantidadeFilmesDisponiveis();
		if (quantidadeDisponivel >= 15) {
			setStatus(StatusEstoque.DISPONIVEL);
		} else if (quantidadeDisponivel >= 5 && quantidadeDisponivel < 15) {
			setStatus(StatusEstoque.REPOSICAO);
		} else {
			setStatus(StatusEstoque.INDISPONIVEL);
		}
	}

	public StatusEstoque getStatus() {
		return StatusEstoque.toEnum(this.status);
	}

	public void setStatus(StatusEstoque status) {
		this.status = status.getCod();
	}

	public int getQuantidadeFilmesReservados() {
		int quantidade = 0;
		for (Reserva reserva : reservas) {
			quantidade += reserva.getQtdReservada();
		}
		return quantidade;
	}

	public int getQuantidadeFilmesReservadosOnline() {
		int quantidade = 0;
		for (ReservaOnline reserva : reservasOnline) {
			quantidade += reserva.getQtdReservada();
		}
		return quantidade;
	}

	public int getQuantidadeFilmesDisponiveis() {
		int quantidadeTotal = this.getQuantidade();
		int quantidadeReservada = this.getQuantidadeFilmesReservados();
		int quantidadeReservadaOnline = this.getQuantidadeFilmesReservadosOnline();
		int quantidadeDisponivel = quantidadeTotal - quantidadeReservada - quantidadeReservadaOnline;
		return quantidadeDisponivel;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<ReservaOnline> getReservasOnline() {
		return reservasOnline;
	}

	public void setReservasOnline(List<ReservaOnline> reservasOnline) {
		this.reservasOnline = reservasOnline;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}