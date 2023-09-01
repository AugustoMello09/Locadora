package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;
import com.io.github.AugustoMello09.Locadora.entity.Locacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocacaoDTOPList implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private int qtd;
	
	private FormaPagamento formaPagamento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataLocacao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataDevolucao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataMaxDevolucao;
	
	public LocacaoDTOPList() {}
	
	public LocacaoDTOPList(Locacao entity) {
		id = entity.getId();
		qtd = entity.getQtd();
		formaPagamento = entity.getformaPagamento();
		dataDevolucao = entity.getDataDevolucao();
		dataLocacao = entity.getDataLocacao();
		dataMaxDevolucao = entity.getDataMaxDevolucao();
	}
	
}
