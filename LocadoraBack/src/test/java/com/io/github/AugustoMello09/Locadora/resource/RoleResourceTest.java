package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.RoleDTO;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;
import com.io.github.AugustoMello09.Locadora.resources.RoleResource;
import com.io.github.AugustoMello09.Locadora.service.RoleService;

@SpringBootTest
public class RoleResourceTest {

	private static final long ID = 1L;

	private static final String NOME = "As";

	@InjectMocks
	private RoleResource resource;

	@Mock
	private RoleRepository repository;

	@Mock
	private RoleService service;

	private RoleDTO roleDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startRole();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(roleDTO);
		ResponseEntity<RoleDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(RoleDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getAuthority());
	}

	@Test
	public void testCreate() {
		when(service.create(roleDTO)).thenReturn(roleDTO);
		ResponseEntity<RoleDTO> response = resource.create(roleDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(RoleDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getAuthority());
		verify(service).create(roleDTO);
	}

	@Test
	public void testDelete() {
		doNothing().when(service).delete(anyLong());
		ResponseEntity<Void> response = resource.delete(ID);
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		verify(service, times(1)).delete(anyLong());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	private void startRole() {
		roleDTO = new RoleDTO(ID, NOME);
	}
}
