package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComBoleto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoComBoletoDTO extends PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataGerada;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoletoDTO() {}
	
	public PagamentoComBoletoDTO(PagamentoComBoleto entity) {
		this.dataVencimento = entity.getDataVencimento();
		this.dataGerada = entity.getDataGerada();
		this.dataPagamento = entity.getDataPagamento();
	}
	
	
	
	

}
