package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_filme")
public class Filme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String diretor;
	private Double valorAluguel;

	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "estoque_id", referencedColumnName = "id")
	private Estoque estoque;

	@OneToMany(mappedBy = "filme")
	private Set<Locacao> locacoes = new HashSet<>();

	public Filme(Long id, String nome, String descricao, String diretor, Double valorAluguel, Categoria categoria,
			Estoque estoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.diretor = diretor;
		this.valorAluguel = valorAluguel;
		this.categoria = categoria;
		this.estoque = estoque;
	}

}
