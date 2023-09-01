package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInsertDTO  extends UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Size(min = 2, max = 24, message = "deve ter entre 2 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String password;
	
}
