package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CategoriaDTO;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOInfo;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.FilmePagedDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@SpringBootTest
public class FilmeServiceTest {

	private static final int INDEX = 0;

	private static final double PRESO = 19.0;

	private static final String DESCRIÇAO = "teste";

	private static final String NOME = "teste";

	private static final StatusEstoque UNDEFINED = StatusEstoque.UNDEFINED;

	private static final int QUANTIDADE = 15;

	private static final long ID = 1L;

	@Mock
	private FilmeRepository repository;

	@Mock
	private CategoriaService categoriaService;

	@Mock
	private CategoriaRepository categoriaRepository;

	@Mock
	private EstoqueRepository estoqueRepository;

	@InjectMocks
	private FilmeService service;

	private Optional<Filme> optinalFilme;
	private FilmeDTO filmeDTO;
	private Filme filme;
	private FilmeDTOUpdate filmeDTOUpdate;

	private Optional<Estoque> optinalEstoque;
	private Estoque estoque;
	private EstoqueDTO estoqueDTO;

	private Optional<Categoria> optinalCategoria;
	private Categoria categoria;
	private CategoriaDTO categoriaDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startFilme();
	}

	@Test
	public void whenFindByIdThenReturnFilmeDTO() {
		when(repository.findById(anyLong())).thenReturn(optinalFilme);
		FilmeDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(FilmeDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(DESCRIÇAO, response.getDescricao());
		assertEquals(NOME, response.getDiretor());
		assertEquals(PRESO, response.getValorAluguel());
		assertNotNull(response.getCategoria());
		assertEquals(ID, response.getCategoria().getId());
		assertEquals(NOME, response.getCategoria().getNomeCategoria());
		assertNotNull(response.getEstoque());
		assertEquals(ID, response.getEstoque().getId());
		assertEquals(QUANTIDADE, response.getEstoque().getQuantidade());
		assertEquals(UNDEFINED, response.getEstoque().getStatus());
	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenFindAllThenReturnAnListOfFilms() {
		when(categoriaService.findById(ID)).thenReturn(categoriaDTO);
		when(repository.findAllByCategory(ID)).thenReturn(List.of(filme));
		List<Filme> response = service.findAll(ID);
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Filme.class, response.get(INDEX).getClass());
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NOME, response.get(INDEX).getNome());	
	}

	@Test
	void whenCreateFilmeThenReturnFilmeDTO() {
		Long idCategoria = ID;
		Long idEstoque = ID;
		
		when(estoqueRepository.findById(anyLong())).thenReturn(optinalEstoque);
		when(categoriaRepository.findById(anyLong())).thenReturn(optinalCategoria);
		when(repository.save(any(Filme.class))).thenReturn(filme);
		
		FilmeDTO response = service.create(filmeDTO);
		
		assertEquals(FilmeDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(DESCRIÇAO, response.getDescricao());
		assertEquals(NOME, response.getDiretor());
		assertEquals(PRESO, response.getValorAluguel());
		assertNotNull(response.getCategoria());
		assertNotNull(response.getEstoque());
		
		verify(estoqueRepository).findById(idEstoque);
		verify(categoriaRepository).findById(idCategoria);
		verify(repository).save(any(Filme.class));
	}

	@Test
	void whenUpdateThenReturnFilmeDTO() {
		when(repository.findById(anyLong())).thenReturn(optinalFilme);
		FilmeDTO response = service.update(filmeDTOUpdate, ID);
		assertNotNull(response);
		assertEquals(FilmeDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(DESCRIÇAO, response.getDescricao());
		assertEquals(NOME, response.getDiretor());
		verify(repository).findById(ID);
		verify(repository).save(any(Filme.class));
	}
	
	@Test
	void whenFindAllThenReturnListOfFilmeDTO() {
        List<Filme> fil = new ArrayList<>();
        fil.add(new Filme(ID, NOME, DESCRIÇAO, NOME, PRESO, categoria, estoque));
        when(repository.findAll()).thenReturn(fil);
        List<FilmeDTOInfo> fildto = service.findAllDrop();
        verify(repository, times(1)).findAll();
        List<FilmeDTOInfo> expectedDTOs = fil.stream().map(FilmeDTOInfo::new).collect(Collectors.toList());
        assertNotNull(expectedDTOs);
        assertNotNull(fildto);
		
	}
	
	
	@Test
	void whenFindAllPagedThenReturnPageOfFilmeDTO() {
		List<Filme> fill = Arrays.asList(new Filme(ID, NOME, DESCRIÇAO, DESCRIÇAO, null, categoria, estoque));
		Page<Filme> fillPage = new PageImpl<>(fill);
		when(repository.findAll(any(Pageable.class))).thenReturn(fillPage);
		Page<FilmePagedDTO> result = service.findPaged(PageRequest.of(0, 5));
		assertNotNull(result);
		for (FilmePagedDTO filDTO : result) {
			assertEquals(FilmePagedDTO.class, filDTO.getClass());
		}
	}

	private void startFilme() {
		filmeDTOUpdate = new FilmeDTOUpdate(NOME, DESCRIÇAO, NOME);
		categoria = new Categoria(ID, NOME);
		estoque = new Estoque(ID, QUANTIDADE, UNDEFINED);
		filme = new Filme(ID, NOME, DESCRIÇAO, NOME, PRESO, categoria, estoque);
		optinalFilme = Optional.of(filme);
		optinalCategoria = Optional.of(categoria);
		optinalEstoque = Optional.of(estoque);
		categoriaDTO = new CategoriaDTO(ID, NOME);
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, UNDEFINED, null, null, QUANTIDADE, QUANTIDADE, QUANTIDADE);
		filmeDTO = new FilmeDTO(ID, NOME, DESCRIÇAO, NOME, PRESO, categoriaDTO, estoqueDTO);
	}

}
