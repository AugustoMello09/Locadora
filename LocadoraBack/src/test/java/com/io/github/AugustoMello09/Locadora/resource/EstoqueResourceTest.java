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
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.resources.EstoqueResource;
import com.io.github.AugustoMello09.Locadora.service.EstoqueService;

@SpringBootTest
public class EstoqueResourceTest {

	private static final StatusEstoque UNDEFINED = StatusEstoque.UNDEFINED;

	private static final int QUANTIDADE = 15;

	private static final long ID = 1L;

	@Mock
	private EstoqueService service;

	@Mock
	private EstoqueRepository repository;

	@InjectMocks
	private EstoqueResource resource;

	private EstoqueDTO estoqueDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEstoque();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findEstoqueById(anyLong())).thenReturn(estoqueDTO);
		ResponseEntity<EstoqueDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(EstoqueDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQuantidade());
		assertEquals(UNDEFINED, response.getBody().getStatus());
	}
	
	@Test
	void whenFindAllThenReturnEstoqueDTO() {
		 List<EstoqueDTO> estoquesDTOs = new ArrayList<>();
	     estoquesDTOs.add(new EstoqueDTO(ID, QUANTIDADE, UNDEFINED, null, null, QUANTIDADE, QUANTIDADE, QUANTIDADE));
	     when(service.findAll()).thenReturn(estoquesDTOs);  
	     ResponseEntity<List<EstoqueDTO>> response = resource.findAll();
	     assertNotNull(response);
	     assertNotNull(response.getBody());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testCreate() {
		when(service.create(any(EstoqueDTO.class))).thenReturn(estoqueDTO);
		ResponseEntity<EstoqueDTO> response = resource.create(estoqueDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQuantidade());
		assertEquals(UNDEFINED, response.getBody().getStatus());
		verify(service).create(estoqueDTO);
	}

	@Test
	public void testUpdate() {
		when(service.update(estoqueDTO, ID)).thenReturn(estoqueDTO);
		ResponseEntity<EstoqueDTO> response = resource.update(estoqueDTO, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQuantidade());
		assertEquals(UNDEFINED, response.getBody().getStatus());
		verify(service).update(estoqueDTO, ID);
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

	private void startEstoque() {
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, UNDEFINED, null, null, QUANTIDADE, QUANTIDADE, QUANTIDADE);
	}
}
