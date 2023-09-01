package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComPix;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoComPixDTO extends PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;
	
	public PagamentoComPixDTO(){}
	
	PagamentoComPixDTO(PagamentoComPix entity){
		this.dataPagamento = entity.getDataPagamento();
	}
	
	
}
