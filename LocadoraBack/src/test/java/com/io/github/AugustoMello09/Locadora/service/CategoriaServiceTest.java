package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CategoriaDTO;
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;

@SpringBootTest
public class CategoriaServiceTest {

	private static final String NOME_CATEGORIA = "anime";

	private static final long ID = 1L;

	@Mock
	private CategoriaRepository repository;

	@InjectMocks
	private CategoriaService service;

	private CategoriaDTO categoriaDTO;

	private Optional<Categoria> optionalCategoria;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCategoria();
	}

	@Test
	void whenFindByIdThenReturnCategoriaDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalCategoria);
		CategoriaDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(CategoriaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME_CATEGORIA, response.getNomeCategoria());
	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenFindAllPagedThenReturnPageOfCategoriaDTO() {
		List<Categoria> categorias = Arrays.asList(new Categoria(ID, NOME_CATEGORIA), new Categoria(2L, "Categoria 2"));
		Page<Categoria> categoriaPage = new PageImpl<>(categorias);
		when(repository.findAll(any(Pageable.class))).thenReturn(categoriaPage);
		Page<CategoriaDTO> result = service.findAllPaged(PageRequest.of(0, 5));
		assertNotNull(result);
		assertEquals(2, result.getTotalElements());
		assertEquals(2, result.getContent().size());
		for (CategoriaDTO categoriaDTO : result) {
			assertEquals(CategoriaDTO.class, categoriaDTO.getClass());
		}
	}

	@Test
	void whenFindAllPagedThenReturnEmptyPage() {
		Page<Categoria> categoriaPage = new PageImpl<>(Collections.emptyList());
		when(repository.findAll(any(Pageable.class))).thenReturn(categoriaPage);
		Page<CategoriaDTO> result = service.findAllPaged(PageRequest.of(0, 10));
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void whenCreateCategoriaThenReturnCategoriaDTO() {
		Categoria categoria = new Categoria(ID, NOME_CATEGORIA);
		when(repository.save(any(Categoria.class))).thenReturn(categoria);
		CategoriaDTO response = service.create(categoriaDTO);
		assertNotNull(response);
		assertEquals(CategoriaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME_CATEGORIA, response.getNomeCategoria());
	}

	@Test
	void whenUpdateCategoriaThenReturnCategoriaDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalCategoria);
		CategoriaDTO response = service.update(categoriaDTO, ID);
		assertNotNull(response);
		assertEquals(CategoriaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME_CATEGORIA, response.getNomeCategoria());
	}
	
	@Test
	void whenDeleteCategoriaWithAssociatedDataThenThrowDataIntegrityViolationException() {
	    Long id = ID;
	    when(repository.findById(id)).thenReturn(optionalCategoria);
	    doThrow(DataIntegrityViolationException.class).when(repository).deleteById(ID);
	    assertThrows(DataIntegratyViolationException.class, () -> service.delete(ID));
	}

	@Test
	void whenDeleteCategoriaThenDeleteSuccessfully() {
		Long categoriaId = ID;
		when(repository.findById(categoriaId)).thenReturn(optionalCategoria);
		service.delete(categoriaId);
		verify(repository).findById(categoriaId);
		verify(repository).deleteById(categoriaId);
	}

	private void startCategoria() {
		categoriaDTO = new CategoriaDTO(ID, NOME_CATEGORIA);
		Categoria categoria = new Categoria(ID, NOME_CATEGORIA);
		optionalCategoria = Optional.of(categoria);
	}

}
