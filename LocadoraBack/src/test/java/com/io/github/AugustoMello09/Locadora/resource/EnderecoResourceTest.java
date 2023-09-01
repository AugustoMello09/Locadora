package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.dto.EnderecoDTO;
import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.resources.EnderecoResource;
import com.io.github.AugustoMello09.Locadora.service.EnderecoService;

@SpringBootTest
public class EnderecoResourceTest {

	private static final String CEP = "38777012";

	private static final String BAIRRO = "Centro";

	private static final String COMPLEMENTO = "Sala 800";

	private static final String NUMERO = "105";

	private static final String RUA = "Avenida Matos";

	private static final String ESTADO = "As";

	private static final String CIDADE = "São";

	private static final long ID = 1L;

	private EstadoDTO estadoDTO;
	private CidadeDTO cidadeDTO;
	private EnderecoDTO enderecoDTO;
	private UserDTO userDTO;

	@Mock
	private EnderecoService service;

	@InjectMocks
	private EnderecoResource resource;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEndereco();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(enderecoDTO);
		ResponseEntity<EnderecoDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(EnderecoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(RUA, response.getBody().getLogradouro());
		assertEquals(NUMERO, response.getBody().getNumero());
		assertEquals(COMPLEMENTO, response.getBody().getComplemento());
		assertEquals(BAIRRO, response.getBody().getBairro());
		assertEquals(CEP, response.getBody().getCep());
		assertNotNull(response.getBody().getCidade());
		assertEquals(ID, response.getBody().getCidade().getId());
		assertEquals(CIDADE, response.getBody().getCidade().getName());
		assertNotNull(response.getBody().getCidade().getEstadoId());
		assertEquals(ID, response.getBody().getCidade().getEstadoId().getId());
		assertEquals(ESTADO, response.getBody().getCidade().getEstadoId().getName());
	}

	@Test
	public void testCreate() {
		when(service.create(any(EnderecoDTO.class))).thenReturn(enderecoDTO);
		ResponseEntity<EnderecoDTO> response = resource.create(enderecoDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(EnderecoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(RUA, response.getBody().getLogradouro());
		assertEquals(NUMERO, response.getBody().getNumero());
		assertEquals(COMPLEMENTO, response.getBody().getComplemento());
		assertEquals(BAIRRO, response.getBody().getBairro());
		assertEquals(CEP, response.getBody().getCep());
		assertNotNull(response.getBody().getCidade());
		assertEquals(ID, response.getBody().getCidade().getId());
		assertEquals(CIDADE, response.getBody().getCidade().getName());
		assertNotNull(response.getBody().getCidade().getEstadoId());
		assertEquals(ID, response.getBody().getCidade().getEstadoId().getId());
		assertEquals(ESTADO, response.getBody().getCidade().getEstadoId().getName());
		verify(service).create(enderecoDTO);
	}

	private void startEndereco() {
		estadoDTO = new EstadoDTO(ID, ESTADO);
		cidadeDTO = new CidadeDTO(ID, CIDADE, estadoDTO);
		enderecoDTO = new EnderecoDTO(ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, cidadeDTO, userDTO);
		userDTO = new UserDTO(ID, "oi", "oi", "oi", null);
		
	}

}
