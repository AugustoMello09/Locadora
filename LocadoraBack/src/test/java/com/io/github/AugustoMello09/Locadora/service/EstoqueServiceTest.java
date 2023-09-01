package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@SpringBootTest
public class EstoqueServiceTest {

	private static final StatusEstoque UNDEFINED = StatusEstoque.UNDEFINED;

	private static final int QUANTIDADE = 15;

	private static final long ID = 1L;

	@Mock
	private EstoqueRepository repository;

	@Mock
	private FilmeRepository filmeRepository;

	@InjectMocks
	private EstoqueService service;

	private Optional<Estoque> optionalEstoque;
	private Estoque estoque;
	private EstoqueDTO estoqueDTO;

	private Filme filme;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEstoque();
	}

	private void startEstoque() {
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, UNDEFINED, null, null, QUANTIDADE, QUANTIDADE, QUANTIDADE);
		estoque = new Estoque(ID, QUANTIDADE, UNDEFINED);
		optionalEstoque = Optional.of(estoque);
		filme = new Filme();
	}

	@Test
	public void whenFindByIdThenReturnEstoqueDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalEstoque);
		EstoqueDTO response = service.findEstoqueById(ID);
		assertNotNull(response);
		assertEquals(EstoqueDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQuantidade());
		assertEquals(UNDEFINED, response.getStatus());
	}
	
	@Test
	public void whenFindEstoqueByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findEstoqueById(ID));
	}
	
	@Test
	public void whenCreateEstoqueThenReturnEstoqueDTO() {
		Estoque estoque = new Estoque();
		when(repository.save(any(Estoque.class))).thenReturn(estoque);
		EstoqueDTO response = service.create(estoqueDTO);
		assertNotNull(response);
		assertEquals(EstoqueDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQuantidade());
		assertEquals(UNDEFINED, response.getStatus());
	}

	@Test
	public void whenUpdateEstoqueThenReturnEstoqueDTO() {
		when(repository.findById(ID)).thenReturn(Optional.of(estoque));
	    when(filmeRepository.findByEstoque(estoque)).thenReturn(Optional.empty());
	    when(repository.save(any(Estoque.class))).thenReturn(estoque);
	    EstoqueDTO response = service.update(estoqueDTO, ID);
	    assertNotNull(response);
	    assertEquals(EstoqueDTO.class, response.getClass());
	    assertEquals(ID, response.getId());
	    assertEquals(QUANTIDADE, response.getQuantidade());
	    assertEquals(StatusEstoque.DISPONIVEL, response.getStatus());
	}
	
	 @Test
	    public void testUpdate_ThrowsDataIntegratyViolationException() {
	        Long estoqueId = ID;
	        Estoque estoque = new Estoque();
	        when(repository.findById(estoqueId)).thenReturn(Optional.of(estoque));
	        when(filmeRepository.findByEstoque(estoque)).thenReturn(Optional.of(filme));
	        assertThrows(DataIntegratyViolationException.class, () -> service.update(estoqueDTO, estoqueId));
	        verify(repository, times(1)).findById(estoqueId);
	        verify(filmeRepository, times(1)).findByEstoque(estoque);
	        verify(repository, never()).save(any(Estoque.class));
	    }
	
	@Test
	void whenDeleteEstoqueWithAssociatedDataThenThrowDataIntegrityViolationException() {
	    Long id = ID;
	    when(repository.findById(id)).thenReturn(Optional.of(estoque));
	    doThrow(DataIntegrityViolationException.class).when(repository).deleteById(ID);
	    assertThrows(DataIntegrityViolationException.class, () -> service.delete(ID));
	}

	@Test
	void whenDeleteEstoqueThenDeleteSuccessfully() {
		Long estoqueId = ID;
		when(repository.findById(estoqueId)).thenReturn(optionalEstoque);
		service.delete(estoqueId);
		verify(repository).findById(estoqueId);
		verify(repository).deleteById(estoqueId);
	}

}
