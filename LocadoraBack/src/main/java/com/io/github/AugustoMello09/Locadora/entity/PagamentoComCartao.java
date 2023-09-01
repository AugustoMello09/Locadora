package com.io.github.AugustoMello09.Locadora.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pagamento_cartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	@Positive
	private int numeroParcelas;

	public PagamentoComCartao(Long id, Double valor, FormaPagamento formaPagamento, Multa multa, int numeroParcelas) {
		super(id, valor, formaPagamento, multa);
		this.numeroParcelas = numeroParcelas;
	}

}
