package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.io.github.AugustoMello09.Locadora.dto.MultaDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComBoletoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComCartaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComPixDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;
import com.io.github.AugustoMello09.Locadora.repositories.MultaRepository;
import com.io.github.AugustoMello09.Locadora.resources.MultaResource;
import com.io.github.AugustoMello09.Locadora.service.MultaService;

@SpringBootTest
public class MultaResourceTeste {

	private static final double MULTA = 80.0;

	private static final long ID = 1L;

	@Mock
	MultaService service;

	@Mock
	MultaRepository repository;

	@InjectMocks
	MultaResource resource;

	private MultaDTO multaDTO;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startMulta();

	}

	@Test
	public void testFindByMulta() {
		when(service.findById(anyLong())).thenReturn(multaDTO);
		ResponseEntity<MultaDTO> response = resource.findByMulta(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(MultaDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(MULTA, response.getBody().getValor());
	}

	@Test
	public void testPagarBoleto() {
		PagamentoComBoletoDTO pagamentoDTO = new PagamentoComBoletoDTO();
		ResponseEntity<Void> response = resource.pagarMultaComBoleto(pagamentoDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).pagarMultaBoleto(pagamentoDTO);
	}

	@Test
	public void testPagarCartao() {
		PagamentoComCartaoDTO pagamentoDTO = new PagamentoComCartaoDTO();
		ResponseEntity<Void> response = resource.pagarMultaComCartão(pagamentoDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).pagarMultaCartao(pagamentoDTO);
	}

	@Test
	public void testPagarPix() {
		PagamentoComPixDTO pagamentoDTO = new PagamentoComPixDTO();
		ResponseEntity<Void> response = resource.pagarMultaComPix(pagamentoDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).pagarMultaPix(pagamentoDTO);
	}

	private void startMulta() {
		multaDTO = new MultaDTO(ID, MULTA, EstadoPagamento.QUITADO);
	}

}
