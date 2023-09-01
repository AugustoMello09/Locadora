package com.io.github.AugustoMello09.Locadora.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusReserva {

	ATIVA(0, "Ativa");

	private Integer cod;
	private String descricao;

	public static StatusReserva toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StatusReserva x : StatusReserva.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Cod invalido" + cod);
	}
}
