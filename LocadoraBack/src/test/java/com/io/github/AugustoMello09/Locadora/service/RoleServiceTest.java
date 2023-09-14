package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.RoleDTO;
import com.io.github.AugustoMello09.Locadora.entity.Role;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;

@SpringBootTest
public class RoleServiceTest {

	private static final String NOME_ROLE = "ADM";

	private static final long ID = 1L;

	@Mock
	private RoleRepository repository;

	@InjectMocks
	private RoleService service;

	private Role role;

	private RoleDTO roleDTO;

	private Optional<Role> optionalRole;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startRole();
	}

	@Test
	void whenFindByIdThenReturnRoleDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalRole);
		RoleDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(RoleDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME_ROLE, response.getAuthority());
	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenCreateRoleThenReturnRoleDTO() {
		Role role = new Role(ID, NOME_ROLE);
		when(repository.save(any(Role.class))).thenReturn(role);
		RoleDTO response = service.create(roleDTO);
		assertNotNull(response);
		assertEquals(RoleDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME_ROLE, response.getAuthority());
	}
	
	@Test
	void whenFindAllThenReturnListOfCategoriaDTO() {
        List<Role> ro = new ArrayList<>();
        ro.add(new Role(1L, "Dono"));
        when(repository.findAll()).thenReturn(ro);
        List<RoleDTO> dTOs = service.findAll();
        verify(repository, times(1)).findAll();
        List<RoleDTO> expectedDTOs = ro.stream().map(RoleDTO::new).collect(Collectors.toList());
        assertNotNull(expectedDTOs);
        assertNotNull(dTOs);
		
	}

	@Test
	void whenDeleteRoleWithAssociatedDataThenThrowDataIntegrityViolationException() {
		Long id = ID;
		when(repository.findById(id)).thenReturn(optionalRole);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(ID);
		assertThrows(DataIntegratyViolationException.class, () -> service.delete(ID));
	}

	@Test
	void whenDeleteRoleThenDeleteSuccessfully() {
		Long roleId = ID;
		when(repository.findById(roleId)).thenReturn(optionalRole);
		service.delete(roleId);
		verify(repository).findById(roleId);
		verify(repository).deleteById(roleId);
	}

	private void startRole() {
		roleDTO = new RoleDTO(ID, NOME_ROLE);
		role = new Role(ID, NOME_ROLE);
		optionalRole = Optional.of(role);
	}
}
