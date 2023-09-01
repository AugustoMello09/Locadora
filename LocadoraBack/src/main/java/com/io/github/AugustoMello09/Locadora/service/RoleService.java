package com.io.github.AugustoMello09.Locadora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.RoleDTO;
import com.io.github.AugustoMello09.Locadora.entity.Role;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public RoleDTO findById(Long id) {
		Optional<Role> obj = repository.findById(id);
		Role entity = obj.orElseThrow(() -> new ObjectNotFoundException("Role não encontrada"));
		return new RoleDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public RoleDTO create(RoleDTO objDto) {
		Role entity = new Role();
		entity.setId(objDto.getId());
		entity.setAuthority(objDto.getAuthority());
		repository.save(entity);
		return new RoleDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<RoleDTO> findAll() {
		List<Role> entity = repository.findAll();
		List<RoleDTO> listDto = entity.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
		return listDto;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar Cargos que estão em uso");
		}

	}


}
