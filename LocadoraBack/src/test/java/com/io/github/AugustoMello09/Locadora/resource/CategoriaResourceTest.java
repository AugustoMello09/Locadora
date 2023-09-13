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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.CategoriaDTO;
import com.io.github.AugustoMello09.Locadora.resources.CategoriaResource;
import com.io.github.AugustoMello09.Locadora.service.CategoriaService;

@SpringBootTest
public class CategoriaResourceTest {

	private static final String NOME_CATEGORIA = "Anime";

	private static final long ID = 1L;

	@Mock
	private CategoriaService service;

	@InjectMocks
	private CategoriaResource resource;
	
	private CategoriaDTO categoriaDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCategoria();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(categoriaDTO);
		ResponseEntity<CategoriaDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME_CATEGORIA, response.getBody().getNomeCategoria());

	}
	
	@Test
	void whenFindAllThenReturnCategoriaDTO() {
		 List<CategoriaDTO> categoriaDTOs = new ArrayList<>();
	        categoriaDTOs.add(new CategoriaDTO(1L, "Categoria1"));
	        categoriaDTOs.add(new CategoriaDTO(2L, "Categoria2"));
	     
	     when(service.findAll()).thenReturn(categoriaDTOs);  
	     
	     ResponseEntity<List<CategoriaDTO>> response = resource.findAll();
	     
	     assertNotNull(response);
	     assertNotNull(response.getBody());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void whenFindAllThenReturnSuccess() {
		List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
		categoriaDTOList.add(new CategoriaDTO(ID, NOME_CATEGORIA));
		categoriaDTOList.add(new CategoriaDTO(2L, "Categoria 2"));
		categoriaDTOList.add(new CategoriaDTO(3L, "Categoria 3"));
		Pageable pageable = Pageable.unpaged();
		Page<CategoriaDTO> categoriaPage = new PageImpl<>(categoriaDTOList, pageable, categoriaDTOList.size());
		when(service.findAllPaged(pageable)).thenReturn(categoriaPage);
		ResponseEntity<Page<CategoriaDTO>> response = resource.findAllPaged(pageable);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(categoriaDTOList.size(), response.getBody().getSize());
		assertEquals(categoriaDTOList.get(0).getId(), response.getBody().getContent().get(0).getId());
		assertEquals(categoriaDTOList.get(1).getNomeCategoria(),
				response.getBody().getContent().get(1).getNomeCategoria());
	}

	@Test
	public void testCreate() {
		when(service.create(any(CategoriaDTO.class))).thenReturn(categoriaDTO);
		ResponseEntity<CategoriaDTO> response = resource.create(categoriaDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME_CATEGORIA, response.getBody().getNomeCategoria());
		verify(service).create(categoriaDTO);
	}

	@Test
	public void testUpdate() {
		when(service.update(categoriaDTO, ID)).thenReturn(categoriaDTO);
		ResponseEntity<CategoriaDTO> response = resource.update(categoriaDTO, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME_CATEGORIA, response.getBody().getNomeCategoria());
		verify(service).update(categoriaDTO, ID);
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

	private void startCategoria() {
		categoriaDTO = new CategoriaDTO(ID, NOME_CATEGORIA);
	}
}
