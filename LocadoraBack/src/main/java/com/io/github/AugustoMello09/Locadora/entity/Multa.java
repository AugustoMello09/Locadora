package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_multa")
public class Multa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double valor;
	
	private Integer formaPagamento;

	@ManyToOne
	@JoinColumn(name = "locacao_id", referencedColumnName = "id")
	private Locacao locacao;

	@OneToMany(mappedBy = "multa")
	private List<Pagamento> pagamentos = new ArrayList<>();

	public Multa(Long id, Double valor, Locacao locacao, EstadoPagamento formaPagamento) {
		super();
		this.id = id;
		this.valor = valor;
		this.locacao = locacao;
		this.formaPagamento = (formaPagamento == null) ? 0 : formaPagamento.getCod();
		
	}
	
	public EstadoPagamento getformaPagamento() {
		return EstadoPagamento.toEnum(this.formaPagamento);
	}

	public void setformaPamento(EstadoPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCod();
	}

}
