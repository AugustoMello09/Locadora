package com.io.github.AugustoMello09.Locadora.dto;

import com.io.github.AugustoMello09.Locadora.entity.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoInsertDTO  extends EnderecoDTO{
	private static final long serialVersionUID = 1L;
	
	private CidadeDTO cidade;
	private UserDTO user;
	
	
	public EnderecoInsertDTO(Endereco entity) {
		super(entity);
		cidade = new CidadeDTO(entity.getCidade());
		user = new UserDTO(entity.getUser());
	}

}
