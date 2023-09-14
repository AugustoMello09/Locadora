package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOInfo;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.UserInsertDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserPagedDTO;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;
import com.io.github.AugustoMello09.Locadora.resources.UserResource;
import com.io.github.AugustoMello09.Locadora.service.UserService;

@SpringBootTest
public class UserResourceTest {

	private static final String NOME = "Anime";
	private static final String EMAIL = "Anime@gmail.com";
	private static final String CPF = "799.456.132-78";

	private static final long ID = 1L;
	
	private UserInsertDTO userInsertDTO;
	
	@Mock
	private UserService service;

	@InjectMocks
	private UserResource resource;

	@Mock
	private UserRepository repository;

	private UserDTO userDTO;
	private UserDTOUpdate userDTOUpdate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(userDTO);
		ResponseEntity<UserDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(CPF, response.getBody().getCpf());

	}

	@Test
	void whenFindAllThenReturnSuccess() {
		Pageable pageable = PageRequest.of(0, 10); 
		Page<UserPagedDTO> page = new PageImpl<>(Collections.emptyList(), pageable, 0); 
		when(service.findAllPaged(pageable)).thenReturn(page);

		ResponseEntity<Page<UserPagedDTO>> response = resource.findAll(pageable);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(page, response.getBody());
		verify(service).findAllPaged(pageable);
	}

	@Test
	public void testCreate() {
		when(service.create(any(UserInsertDTO.class))).thenReturn(userDTO);
		ResponseEntity<UserDTO> response = resource.create(userInsertDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(CPF, response.getBody().getCpf());
		verify(service).create(userInsertDTO);
	}

	@Test
	public void testUpdate() {
		when(service.update(userDTOUpdate, ID)).thenReturn(userDTO);
		ResponseEntity<UserDTO> response = resource.update(ID, userDTOUpdate);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		verify(service).update(userDTOUpdate, ID);
	}
	
	@Test
	public void testUpdateCargo() {
		when(service.cargo(userDTO, ID)).thenReturn(userDTO);
		ResponseEntity<UserDTO> response = resource.cargo(userDTO, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).cargo(userDTO, ID);
	}
	
	@Test
	void whenFindAllThenReturnUserDTO() {
		 List<UserDTOInfo> dTOs = new ArrayList<>();
	        dTOs.add(new UserDTOInfo(ID, CPF, EMAIL));
	        
	     
	     when(service.findAllDrop()).thenReturn(dTOs);  
	     
	     ResponseEntity<List<UserDTOInfo>> response = resource.findAllDrop();
	     
	     assertNotNull(response);
	     assertNotNull(response.getBody());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
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

	private void startUser() {
		userInsertDTO = new UserInsertDTO("132");
		userDTOUpdate = new UserDTOUpdate(NOME, EMAIL);
		userDTO = new UserDTO(ID, NOME, EMAIL, CPF, null);
	}
	
}
