package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.MultaDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComBoletoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComCartaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComPixDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;
import com.io.github.AugustoMello09.Locadora.entity.Multa;
import com.io.github.AugustoMello09.Locadora.repositories.MultaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.PagamentoRepository;

@SpringBootTest
public class MultaServiceTest {

	private static final double PRESO = 19.0;

	private static final long ID = 1L;

	@Mock
	private MultaRepository repository;

	@Mock
	private PagamentoRepository pagamentoRepository;

	@InjectMocks
	private MultaService service;

	private Optional<Multa> optionalMulta;
	private Multa multa;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startMulta();
	}

	
	@Test 
	public void whenFindByIdThenReturnMultaDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalMulta);
		MultaDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(MultaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(PRESO, response.getValor());
	}

	@Test 
	public void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}
	
	@Test
	void whenFindAllThenReturnListOfMultaDTO() {
        List<Multa> mu = new ArrayList<>();
        mu.add(new Multa(ID, PRESO, null, EstadoPagamento.CANCELADO));
        when(repository.findAll()).thenReturn(mu);
        List<MultaDTO> muDTOs = service.findAll();
        verify(repository, times(1)).findAll();
        List<MultaDTO> expectedDTOs = mu.stream().map(MultaDTO::new).collect(Collectors.toList());
        assertNotNull(expectedDTOs);
        assertNotNull(muDTOs);
		
	}

	 @Test
	 public void testPagarMultaCartao_MultaExistente() {
	        PagamentoComCartaoDTO pagamentoDTO = new PagamentoComCartaoDTO();
	        pagamentoDTO.setValor(100.0);
	        MultaDTO multaDTO = new MultaDTO();
	        multaDTO.setId(1L);
	        pagamentoDTO.setMulta(multaDTO);
	        Multa multa = new Multa();
	        multa.setId(1L);
	        multa.setValor(50.0);
	        when(repository.findById(1L)).thenReturn(Optional.of(multa));
	        service.pagarMultaCartao(pagamentoDTO);
	        verify(pagamentoRepository, times(1)).save(any());
	  }
	 
	 @Test
	 public void testPagarMultaCartao_MultaNaoEncontrada() {
	        PagamentoComCartaoDTO pagamentoDTO = new PagamentoComCartaoDTO();
	        pagamentoDTO.setValor(100.0);
	        MultaDTO multaDTO = new MultaDTO();
	        multaDTO.setId(1L);
	        pagamentoDTO.setMulta(multaDTO);
	        when(repository.findById(anyLong())).thenReturn(Optional.empty());
	        assertThrows(ObjectNotFoundException.class, () -> service.pagarMultaCartao(pagamentoDTO));
	        verify(pagamentoRepository, never()).save(any());
	 }
	 
	 @Test
	 public void testPagarMultaPix_MultaNaoEncontrada() {
		 PagamentoComPixDTO pagamentoDTO = new PagamentoComPixDTO();
		 pagamentoDTO.setValor(100.0);
		 MultaDTO multaDTO = new MultaDTO();
		 multaDTO.setId(1L);
		 pagamentoDTO.setMulta(multaDTO);
		 when(repository.findById(anyLong())).thenReturn(Optional.empty());
		 assertThrows(ObjectNotFoundException.class, () -> service.pagarMultaPix(pagamentoDTO));
		 verify(pagamentoRepository, never()).save(any());
	 }
	 
	 @Test
	 public void testPagarMultaBoleto_MultaNaoEncontrada() {
		 PagamentoComBoletoDTO pagamentoDTO = new PagamentoComBoletoDTO();
		 pagamentoDTO.setValor(100.0);
		 MultaDTO multaDTO = new MultaDTO();
		 multaDTO.setId(1L);
		 pagamentoDTO.setMulta(multaDTO);
		 when(repository.findById(anyLong())).thenReturn(Optional.empty());
		 assertThrows(ObjectNotFoundException.class, () -> service.pagarMultaBoleto(pagamentoDTO));
		 verify(pagamentoRepository, never()).save(any());
	 }
	 
	  @Test
	  public void testPagarMultaPix_MultaExistente() {
	        PagamentoComPixDTO pagamentoDTO = new PagamentoComPixDTO();
	        pagamentoDTO.setValor(100.0);
	        MultaDTO multaDTO = new MultaDTO();
	        multaDTO.setId(1L);
	        pagamentoDTO.setMulta(multaDTO);
	        Multa multa = new Multa();
	        multa.setId(1L);
	        multa.setValor(50.0);
	        when(repository.findById(1L)).thenReturn(Optional.of(multa));
	        service.pagarMultaPix(pagamentoDTO);
	        verify(pagamentoRepository, times(1)).save(any());
	    }

	    @Test
	    public void testPagarMultaBoleto_MultaExistente() {
	        PagamentoComBoletoDTO pagamentoDTO = new PagamentoComBoletoDTO();
	        pagamentoDTO.setValor(100.0);
	        MultaDTO multaDTO = new MultaDTO();
	        multaDTO.setId(1L);
	        pagamentoDTO.setMulta(multaDTO);
	        Multa multa = new Multa();
	        multa.setId(1L);
	        multa.setValor(50.0);
	        when(repository.findById(1L)).thenReturn(Optional.of(multa));
	        service.pagarMultaBoleto(pagamentoDTO);
	        verify(pagamentoRepository, times(1)).save(any());
	    }

	private void startMulta() {
		multa = new Multa(ID, PRESO, null, EstadoPagamento.CANCELADO);
		optionalMulta = Optional.of(multa);
	}

}
