package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTOPList;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;
import com.io.github.AugustoMello09.Locadora.repositories.LocacaoRepository;
import com.io.github.AugustoMello09.Locadora.resources.LocacaoResource;
import com.io.github.AugustoMello09.Locadora.service.LocacaoService;

@SpringBootTest
public class LocacaoResourceTest {

	private static final FormaPagamento BOLETO = FormaPagamento.BOLETO;

	private static final String CPF = "cpf";

	private static final String EMAIL = "email";

	private static final double PRESO = 19.0;

	private static final String DESCRIÇAO = "teste";

	private static final String NOME = "teste";

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private LocacaoService service;

	@Mock
	private LocacaoRepository repository;

	@InjectMocks
	private LocacaoResource resource;

	private LocacaoDTO locacaoDTO;

	private FilmeDTO filmeDTO;

	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startlocacao();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(locacaoDTO);
		ResponseEntity<LocacaoDTO> response = resource.findByIdDto(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(LocacaoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQtd());
		assertEquals(BOLETO, response.getBody().getFormaPagamento());
		assertNotNull(response.getBody().getUser());
		assertEquals(ID, response.getBody().getUser().getId());
		assertEquals(NOME, response.getBody().getUser().getName());
		assertEquals(EMAIL, response.getBody().getUser().getEmail());
		assertEquals(CPF, response.getBody().getUser().getCpf());
		assertNotNull(response.getBody().getFilme());
		assertEquals(ID, response.getBody().getFilme().getId());
		assertEquals(NOME, response.getBody().getFilme().getNome());
		assertEquals(DESCRIÇAO, response.getBody().getFilme().getDescricao());
		assertEquals(NOME, response.getBody().getFilme().getDiretor());
		assertEquals(PRESO, response.getBody().getFilme().getValorAluguel());
	}

	@Test
	public void testDevolver() {
		ResponseEntity<Void> response = resource.devolver(ID, locacaoDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).devolver(ID, locacaoDTO);
	}
	
	@Test
	void whenFindAllThenReturnLocacaoDTO() {
		 List<LocacaoDTOPList> li = new ArrayList<>();
	        li.add(new LocacaoDTOPList(ID, QUANTIDADE, BOLETO, null, null, null));
	       
	     
	     when(service.findAll()).thenReturn(li);  
	     
	     ResponseEntity<List<LocacaoDTOPList>> response = resource.findAll();
	     
	     assertNotNull(response);
	     assertNotNull(response.getBody());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testCreate() {
		when(service.create(locacaoDTO)).thenReturn(locacaoDTO);
		ResponseEntity<LocacaoDTO> response = resource.create(locacaoDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(LocacaoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQtd());
		assertEquals(BOLETO, response.getBody().getFormaPagamento());
		assertNotNull(response.getBody().getUser());
		assertEquals(ID, response.getBody().getUser().getId());
		assertEquals(NOME, response.getBody().getUser().getName());
		assertEquals(EMAIL, response.getBody().getUser().getEmail());
		assertEquals(CPF, response.getBody().getUser().getCpf());
		assertNotNull(response.getBody().getFilme());
		assertEquals(ID, response.getBody().getFilme().getId());
		assertEquals(NOME, response.getBody().getFilme().getNome());
		assertEquals(DESCRIÇAO, response.getBody().getFilme().getDescricao());
		assertEquals(NOME, response.getBody().getFilme().getDiretor());
		assertEquals(PRESO, response.getBody().getFilme().getValorAluguel());
	}

	private void startlocacao() {
		filmeDTO = new FilmeDTO(ID, NOME, DESCRIÇAO, NOME, PRESO, null, null);
		userDTO = new UserDTO(ID, DESCRIÇAO, EMAIL, CPF, null);
		locacaoDTO = new LocacaoDTO(ID, QUANTIDADE, BOLETO, null, null, null, userDTO, filmeDTO, null);
	}
	
}
