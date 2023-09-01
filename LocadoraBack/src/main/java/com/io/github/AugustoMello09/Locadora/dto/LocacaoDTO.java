package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;
import com.io.github.AugustoMello09.Locadora.entity.Locacao;
import com.io.github.AugustoMello09.Locadora.entity.Multa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Positive
	private int qtd;
	
	private FormaPagamento formaPagamento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataLocacao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataDevolucao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataMaxDevolucao;

	private UserDTO user;
	private FilmeDTO filme;
	
	private List<MultaDTO> multas = new ArrayList<>();
	
	public LocacaoDTO() {}

	public LocacaoDTO(Locacao entity) {
		this.id = entity.getId();
		this.qtd = entity.getQtd();
		this.formaPagamento = entity.getformaPagamento();
		this.dataLocacao = entity.getDataLocacao();
		this.dataDevolucao = entity.getDataDevolucao();
		this.dataMaxDevolucao = entity.getDataMaxDevolucao();
		this.user = new UserDTO(entity.getUser());
		this.filme = new FilmeDTO(entity.getFilme());		
		entity.getMultas().stream()
        .filter(multa -> !isMultaAlreadyAdded(multa))
        .map(MultaDTO::new)
        .forEach(multas::add);
	}
	
	private boolean isMultaAlreadyAdded(Multa multa) {
        return multas.stream().anyMatch(multaDTO -> multaDTO.getId().equals(multa.getId()));
    }
}

