package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
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

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTOInsert;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaOnlineRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class ReservaOnlineServiceTest {

	private static final LocalDate DATA = LocalDate.now();

	private static final StatusReserva ATIVA = StatusReserva.ATIVA;

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private ReservaOnlineRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private EstoqueRepository estoqueRepository;
	
	@Mock
	private AuthService authService;

	@InjectMocks
	private ReservaOnlineService service;

	private ReservaOnline reservaOnline;
	private Optional<ReservaOnline> optionalReservaOnline;
	private Optional<User> optionalUser;
	private Optional<Estoque> optionalEstoque;
	private ReservaOnlineDTOInsert reservaOnlineDTOInsert;
	private EstoqueDTO estoqueDTO;
	private UserDTO userDTO;
	private User user;
	private Estoque estoque;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startReservaOnline();
	}

	@Test 
	public void whenFindByIdThenReturnReservaOnlineDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalReservaOnline);
		ReservaOnlineDTO response = service.findReservaOnlineById(ID);
		assertNotNull(response);
		assertEquals(ReservaOnlineDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQtdReservada());
		assertEquals(DATA, response.getDataReserva());
		assertEquals(ATIVA, response.getStatusReserva());
	}

	@Test 
	public void whenFindReservaOnlineByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findReservaOnlineById(ID));
	}

	@Test 
	public void testCreate_EstoqueNotFound() {
		Long idEstoque = 1L;
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(reservaOnlineDTOInsert));
	}
	
	@Test
	public void whenFindAllThenReturnListOfReservaOnlineDTO() {
        List<ReservaOnline> on = new ArrayList<>();
        on.add(new ReservaOnline(ID, QUANTIDADE, DATA, user, estoque, ATIVA));
     
        when(repository.findAll()).thenReturn(on);
        List<ReservaOnlineDTO> dTOs = service.findAll();
        verify(repository, times(1)).findAll();
        List<ReservaOnlineDTO> expectedDTOs = on.stream().map(ReservaOnlineDTO::new).collect(Collectors.toList());
        assertNotNull(expectedDTOs);
        assertNotNull(dTOs);
		
	}
	
	@Test
	void whenFindAllPagedThenReturnPageOfReservaOnlineDTO() {
			
        User usuarioAutenticado = new User();
        usuarioAutenticado.setId(1L);
        when(authService.authenticated()).thenReturn(usuarioAutenticado);
		
		List<ReservaOnline> re = Arrays.asList(new ReservaOnline(ID, QUANTIDADE, DATA, user, estoque, ATIVA));
		Page<ReservaOnline> rePage = new PageImpl<>(re);
		

		when(repository.findByUser_Id(eq(1L), any(Pageable.class))).thenReturn(rePage);
		
		Page<ReservaOnlineDTO> result = service.findAllPaged(PageRequest.of(0, 5));
		
		assertNotNull(result);
	}

	@Test 
	public void testCreate_UserNotFound() {
		Long idUser = 1L;
		when(userRepository.findById(idUser)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(reservaOnlineDTOInsert));
	}

	@Test 
	public void testCancelarReserva() {
		Long id = ID;
		Estoque estoque = new Estoque();
		estoque.getReservasOnline().add(reservaOnline);
		reservaOnline.setEstoque(estoque);
		when(repository.findById(id)).thenReturn(Optional.of(reservaOnline));
		service.cancelarReserva(id);
		verify(repository, times(1)).findById(id);
		verify(repository, times(1)).delete(reservaOnline);
		assertFalse(estoque.getReservasOnline().contains(reservaOnline));
	}

	@Test 
	public void testCreate() {
		when(repository.save(any(ReservaOnline.class))).thenReturn(reservaOnline);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(estoqueRepository.findById(anyLong())).thenReturn(optionalEstoque);
		ReservaOnlineDTO result = service.create(reservaOnlineDTOInsert);
		assertEquals(ReservaOnlineDTO.class, result.getClass());
		verify(repository, times(1)).save(any(ReservaOnline.class));
	}

	@Test
	public void testCreate_QuantidadeMaiorDoQueDisponivel() {
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(estoqueRepository.findById(anyLong())).thenReturn(optionalEstoque);
		reservaOnlineDTOInsert.setQtdReservada(10);
		Assertions.assertThrows(DataIntegratyViolationException.class, () -> {
			service.create(reservaOnlineDTOInsert);
		});
		verify(repository, never()).save(any(ReservaOnline.class));
	}

	private void startReservaOnline() {
		reservaOnline = new ReservaOnline(ID, QUANTIDADE, DATA, null, null, ATIVA);
		optionalReservaOnline = Optional.of(reservaOnline);
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, StatusEstoque.DISPONIVEL, null, null, QUANTIDADE, QUANTIDADE,
				QUANTIDADE);
		userDTO = new UserDTO(ID, "oi", "oi", "oi", null);
		reservaOnlineDTOInsert = new ReservaOnlineDTOInsert(userDTO, estoqueDTO);
		user = new User(ID, "oi", "oi", "oi", "123");
		estoque = new Estoque(ID, QUANTIDADE, StatusEstoque.DISPONIVEL);
		optionalUser = Optional.of(user);
		optionalEstoque = Optional.of(estoque);

	}

}
