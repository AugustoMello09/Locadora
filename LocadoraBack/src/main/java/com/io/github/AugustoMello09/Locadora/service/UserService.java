package com.io.github.AugustoMello09.Locadora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.RoleDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOInfo;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.UserInsertDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserPagedDTO;
import com.io.github.AugustoMello09.Locadora.entity.Role;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthService authService;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public UserDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		return new UserDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Page<UserPagedDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserPagedDTO(x));
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<UserDTOInfo> findAllDrop() {
		List<User> entity = repository.findAll();
		List<UserDTOInfo> listDto = entity.stream().map(x -> new UserDTOInfo(x))
				.collect(Collectors.toList());
		return listDto;
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public UserDTO create(UserInsertDTO objDto) {
		User entity = new User();
		copyToEntity(objDto, entity);
		entity.setPassword(passwordEncoder.encode(objDto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);

	}

	protected void copyToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setEmail(dto.getEmail());
		for (RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.findById(roleDto.getId()).get();
			entity.getRoles().add(role);
		}
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public UserDTO update(UserDTOUpdate objDto, Long id) {
		authService.validateSelfOrAdmin(id);
		User entity = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		entity.setName(objDto.getName());
		entity.setEmail(objDto.getEmail());
		repository.save(entity);
		return new UserDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public UserDTO cargo(UserDTO fil, Long id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Usuário não encotrado"));
		associateRoles(entity, fil);
		entity = repository.save(entity);
		return new UserDTO(entity);		
	}
	
	protected void associateRoles(User entity, UserDTO dto) {
	    entity.getRoles().clear(); 
	    for (RoleDTO roleDto : dto.getRoles()) {
	        Role role = roleRepository.findById(roleDto.getId())
	                .orElseThrow(() -> new ObjectNotFoundException("Cargo não encontrado"));
	        entity.getRoles().add(role); 
	    }
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode excluir um usuário que tem várias associações");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			log.error("Email não encontrado" + username);
			throw new UsernameNotFoundException("Email não encontrado");
		}
		log.info("Email encontrado: " + username);
		return user;
	}

}
