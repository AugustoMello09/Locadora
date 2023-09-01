package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_locacao")
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int qtd;

	private Integer formaPagamento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataLocacao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataDevolucao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataMaxDevolucao;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "filme_id", referencedColumnName = "id")
	private Filme filme;

	@OneToMany(mappedBy = "locacao", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Multa> multas = new ArrayList<>();

	public Locacao(Long id, int qtd, LocalDateTime dataDevolucao, User user, Filme filme,
			FormaPagamento formaPagamento) {
		this.id = id;
		this.qtd = qtd;
		this.user = user;
		this.filme = filme;
		this.dataDevolucao = dataDevolucao;
		this.setDataLocacao(LocalDateTime.now());
		this.dataMaxDevolucao = this.dataLocacao.plusDays(7);
		this.formaPagamento = (formaPagamento == null) ? 2 : formaPagamento.getCod();
	}

	public FormaPagamento getformaPagamento() {
		return FormaPagamento.toEnum(this.formaPagamento);
	}

	public void setformaPamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCod();
	}

}
