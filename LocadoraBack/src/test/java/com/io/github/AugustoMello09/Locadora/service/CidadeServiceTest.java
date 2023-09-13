package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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
import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstadoRepository;

@SpringBootTest
public class CidadeServiceTest {

	private static final String CIDADE = "Assis";
	private static final String ESTADO = "São Paulo";
	private static final long ID = 1L;

	private Cidade cidade;
	private EstadoDTO estadoDTO;
	private Estado est;

	private Optional<Cidade> optionalCidade;
	private Optional<Estado> optionalEstado;

	@Mock
	private EstadoRepository estadoRepository;

	@Mock
	private CidadeRepository repository;

	@InjectMocks
	private CidadeService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCidade();
	}

	@Test
	void whenFindByIdThenReturnCidadeDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalCidade);
		CidadeDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(CidadeDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(CIDADE, response.getName());
		assertNotNull(response.getEstadoId());
		assertEquals(ID, response.getEstadoId().getId());
		assertEquals(ESTADO, response.getEstadoId().getName());
	}
	
	@Test
	void whenFindByNomeThenReturnSuccess() {
		when(repository.findByName(anyString())).thenReturn(cidade);
		CidadeDTO response = service.findByNome(CIDADE);
		assertNotNull(response);
		assertEquals(CidadeDTO.class, response.getClass());	
	}
	
	@Test
	void whenFindByNomeThenReturnNotFound() {
		when(repository.findByName(anyString())).thenReturn(null);
		assertThrows(ObjectNotFoundException.class, () -> service.findByNome(CIDADE));
	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenCreateCidadeThenReturnCidadeDTO() {
		Long estadoId = ID;
		when(estadoRepository.findById(anyLong())).thenReturn(optionalEstado);
		CidadeDTO cidadeDTO = new CidadeDTO(ID, CIDADE, estadoDTO);
		cidadeDTO.setEstadoId(estadoDTO);
		when(repository.save(any(Cidade.class))).thenReturn(cidade);
		CidadeDTO resposta = service.create(cidadeDTO);
		assertNotNull(resposta);
		assertEquals(CidadeDTO.class, resposta.getClass());
		assertEquals(cidadeDTO.getId(), resposta.getId());
		assertEquals(cidadeDTO.getName(), resposta.getName());
		verify(estadoRepository).findById(estadoId);
		verify(repository).save(any(Cidade.class));
	}

	@Test
	void whenDeleteCidadeWithAssociatedDataThenThrowDataIntegrityViolationException() {
	    Long id = ID;
	    when(repository.findById(id)).thenReturn(Optional.of(cidade));
	    doThrow(DataIntegrityViolationException.class).when(repository).deleteById(ID);
	    assertThrows(DataIntegratyViolationException.class, () -> service.delete(ID));
	}

	@Test
	void whenDeleteCidadeThenDeleteSuccessfully() {
		Long cidadeId = ID;
		when(repository.findById(cidadeId)).thenReturn(optionalCidade);
		service.delete(cidadeId);
		verify(repository).findById(cidadeId);
		verify(repository).deleteById(cidadeId);
	}

	private void startCidade() {
		estadoDTO = new EstadoDTO(ID, CIDADE);
		est = new Estado(ID, ESTADO);
		cidade = new Cidade(ID, CIDADE, est);
		optionalCidade = Optional.of(cidade);
		optionalEstado = Optional.of(est);
	}
	
}
