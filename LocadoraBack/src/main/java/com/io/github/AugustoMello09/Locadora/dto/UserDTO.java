package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 2, max = 50, message = "Deve ter entre 2 a 50 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	@Email
	@Size(min = 2, max = 64, message = "Deve ter entre 2 a 64 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String email;
	
	@CPF
	@NotBlank(message = "Campo obrigatório")
	private String cpf;

	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO() {
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		entity.getRoles().forEach(x -> this.roles.add(new RoleDTO(x)));
		
	}

}
