package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPagedDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 1, max = 50, message = "Deve ter entre 2 a 50 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	@Email
	@Size(min = 1, max = 50, message = "Deve ter entre 2 a 50 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String email;
	
	private Set<RoleDTO> roles = new HashSet<>();

	public UserPagedDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		entity.getRoles().forEach(x -> this.roles.add(new RoleDTO(x)));
	}

}
