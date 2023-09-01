package com.io.github.AugustoMello09.Locadora.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

	PENDENTE(0, "Pendente"), QUITADO(1, "Pago"), CANCELADO(2, "Cancelado");

	private Integer cod;
	private String descricao;

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Cod invalido" + cod);
	}

}
