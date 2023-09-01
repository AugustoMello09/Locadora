package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.CategoriaDTO;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOByCategory;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOUpdate;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;
import com.io.github.AugustoMello09.Locadora.resources.FilmeResource;
import com.io.github.AugustoMello09.Locadora.service.FilmeService;

@SpringBootTest
public class FilmeResourceTest {

	private static final double PRESO = 19.0;
	private static final String DESCRIÇAO = "teste";
	private static final String NOME = "teste";
	private static final StatusEstoque UNDEFINED = StatusEstoque.UNDEFINED;
	private static final int QUANTIDADE = 15;
	private static final String NOME_CATEGORIA = "ação";
	private static final long ID = 1L;

	private FilmeDTO filmeDTO;
	private CategoriaDTO categoriaDTO;
	private EstoqueDTO estoqueDTO;
	private Categoria categoria;
	private Estoque estoque;
	
	
	@Mock
	private EstoqueRepository estoqueRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private FilmeService service;

	@Mock
	private FilmeRepository repository;

	@InjectMocks
	private FilmeResource resource;

	private FilmeDTOUpdate filmeDTOUpdate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startFilme();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(filmeDTO);
		ResponseEntity<FilmeDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(FilmeDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(DESCRIÇAO, response.getBody().getDescricao());
		assertEquals(NOME, response.getBody().getDiretor());
		assertEquals(PRESO, response.getBody().getValorAluguel());
		assertNotNull(response.getBody().getCategoria());
		assertEquals(ID, response.getBody().getCategoria().getId());
		assertEquals(NOME_CATEGORIA, response.getBody().getCategoria().getNomeCategoria());
		assertNotNull(response.getBody().getEstoque());
		assertEquals(ID, response.getBody().getEstoque().getId());
		assertEquals(QUANTIDADE, response.getBody().getEstoque().getQuantidade());
		assertEquals(UNDEFINED, response.getBody().getEstoque().getStatus());
	}

	@Test
	public void testFindAll() {
		Long idCategoria = 1L;
		List<Filme> filmes = Arrays.asList(new Filme(1L, "Nome 1", "Descrição 1", "Nome 1", 12.00, categoria, estoque),
				new Filme(2L, "Nome 2", "Descrição 2", "Nome 2", 13.00, categoria, estoque));
		when(service.findAll(idCategoria)).thenReturn(filmes);
		ResponseEntity<List<FilmeDTOByCategory>> response = resource.findAll(idCategoria);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
	}

	@Test
	public void testCreate() {
		when(service.create(any(FilmeDTO.class))).thenReturn(filmeDTO);
		ResponseEntity<FilmeDTO> response = resource.create(filmeDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(FilmeDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(DESCRIÇAO, response.getBody().getDescricao());
		assertEquals(NOME, response.getBody().getDiretor());
		assertEquals(PRESO, response.getBody().getValorAluguel());
		assertNotNull(response.getBody().getCategoria());
		assertNotNull(response.getBody().getEstoque());
		verify(service).create(filmeDTO);
	}

	@Test
	public void testUpdate() {
		when(service.update(filmeDTOUpdate, ID)).thenReturn(filmeDTO);
		ResponseEntity<FilmeDTO> response = resource.update(ID, filmeDTOUpdate);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(DESCRIÇAO, response.getBody().getDescricao());
		assertEquals(NOME, response.getBody().getDiretor());
		assertEquals(PRESO, response.getBody().getValorAluguel());
		verify(service).update(filmeDTOUpdate, ID);
	}

	private void startFilme() {
		filmeDTOUpdate = new FilmeDTOUpdate(NOME, DESCRIÇAO, NOME);
		categoriaDTO = new CategoriaDTO(ID, NOME_CATEGORIA);
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, UNDEFINED, null, null, 0, 0, QUANTIDADE);
		filmeDTO = new FilmeDTO(ID, NOME, DESCRIÇAO, NOME, PRESO, categoriaDTO, estoqueDTO);
		categoria = new Categoria(ID, NOME_CATEGORIA);
		estoque = new Estoque(ID, QUANTIDADE, UNDEFINED);
	}
	
}
