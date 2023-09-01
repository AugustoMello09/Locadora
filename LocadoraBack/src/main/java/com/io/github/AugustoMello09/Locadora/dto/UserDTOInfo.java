package com.io.github.AugustoMello09.Locadora.dto;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTOInfo {
	
	private Long id;
	private String name;
	private String email;
	
	public UserDTOInfo() {}
	
	public UserDTOInfo(User entity) {
		id = entity.getId();
		name= entity.getName();
		email = entity.getEmail();
	}
}
