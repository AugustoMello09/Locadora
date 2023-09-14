package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTOInsert;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaOnlineRepository;
import com.io.github.AugustoMello09.Locadora.resources.ReservaOnlineResource;
import com.io.github.AugustoMello09.Locadora.service.ReservaOnlineService;

@SpringBootTest
public class ReservaOnlineResourceTest {

	private static final LocalDate DATA = LocalDate.now();

	private static final StatusReserva ATIVA = StatusReserva.ATIVA;

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private ReservaOnlineService service;

	@Mock
	private ReservaOnlineRepository repository;

	@InjectMocks
	private ReservaOnlineResource resource;

	private ReservaOnlineDTO reservaOnlineDTO;
	private ReservaOnlineDTOInsert reservaOnlineDTOInsert;
	private EstoqueDTO estoqueDTO;
	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startReservaOnline();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findReservaOnlineById(anyLong())).thenReturn(reservaOnlineDTO);
		ResponseEntity<ReservaOnlineDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ReservaOnlineDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQtdReservada());
		assertEquals(DATA, response.getBody().getDataReserva());
		assertEquals(ATIVA, response.getBody().getStatusReserva());
	}

	@Test
	void whenFindAllThenReturnSuccess() {
		when(service.create(reservaOnlineDTOInsert)).thenReturn(reservaOnlineDTO);
		ResponseEntity<ReservaOnlineDTO> response = resource.create(reservaOnlineDTOInsert);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQtdReservada());
		assertEquals(DATA, response.getBody().getDataReserva());
		assertEquals(ATIVA, response.getBody().getStatusReserva());
		verify(service).create(reservaOnlineDTOInsert);
	}
	
	@Test
	void whenFindAllThenReturnReservaOnlineDTO() {
		 List<ReservaOnlineDTO> dto = new ArrayList<>();
	        dto.add(new ReservaOnlineDTO(ID, QUANTIDADE, DATA, ATIVA));
	        
	     
	     when(service.findAll()).thenReturn(dto);  
	     
	     ResponseEntity<List<ReservaOnlineDTO>> response = resource.findAll();
	     
	     assertNotNull(response);
	     assertNotNull(response.getBody());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void whenFindAllThenReturnSuccessReserva() {
		List<ReservaOnlineDTO> dTOList = new ArrayList<>();
		dTOList.add(new ReservaOnlineDTO(ID, QUANTIDADE, DATA, ATIVA));
		Pageable pageable = Pageable.unpaged();
		Page<ReservaOnlineDTO> rePage = new PageImpl<>(dTOList, pageable, dTOList.size());
		when(service.findAllPaged(pageable)).thenReturn(rePage);
		ResponseEntity<Page<ReservaOnlineDTO>> response = resource.findAllPaged(pageable);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testDelete() {
		ResponseEntity<Void> response = resource.cancelar(ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).cancelarReserva(ID);
	}

	private void startReservaOnline() {
		reservaOnlineDTO = new ReservaOnlineDTO(ID, QUANTIDADE, DATA, ATIVA);
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, StatusEstoque.DISPONIVEL, null, null, QUANTIDADE, QUANTIDADE, QUANTIDADE);
		userDTO = new UserDTO(ID, "oi", "oi", "oi", null);
		reservaOnlineDTOInsert = new ReservaOnlineDTOInsert(userDTO, estoqueDTO);
	}
	
}
