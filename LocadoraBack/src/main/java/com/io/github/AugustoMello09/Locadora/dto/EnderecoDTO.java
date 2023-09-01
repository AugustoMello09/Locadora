package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 3, max = 45, message = "Deve ter entre 3 a 45 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String logradouro;
	
	@Size(max = 5, message = "Deve ter no máximo 5 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String numero;
	
	@Size(max = 24, message = "Deve ter no máximo 24 caracteres")
	private String complemento;
	
	@Size(min = 2, max = 45, message = "Deve ter entre 2 a 45 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String bairro;
	
	@Size(max = 9, message = "Deve ter no máximo 9 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String cep;
	
	private CidadeDTO cidade;
	private UserDTO user;
	
	public EnderecoDTO() {
	}
	
	public EnderecoDTO(Endereco entity) {
		id = entity.getId();
		logradouro = entity.getLogradouro();
		numero = entity.getNumero();
		complemento = entity.getComplemento();
		bairro = entity.getBairro();
		cep = entity.getCep();
		cidade = new CidadeDTO(entity.getCidade());
		user = new UserDTO(entity.getUser());

	}

}
