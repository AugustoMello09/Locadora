package com.io.github.AugustoMello09.Locadora.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamento {

	CARTAO(0, "Cartão"), BOLETO(1, "Boleto"), PIX(2, "Pix");

	private Integer cod;
	private String descricao;

	public static FormaPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (FormaPagamento x : FormaPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Cod invalido" + cod);
	}
}
