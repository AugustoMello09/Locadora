package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTOInsert;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Reserva;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class ReservaServiceTest {

	private static final LocalDate DATA = LocalDate.now();

	private static final StatusReserva ATIVA = StatusReserva.ATIVA;

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private ReservaRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private EstoqueRepository estoqueRepository;

	@InjectMocks
	private ReservaService service;

	private Reserva reserva;
	private Optional<Reserva> optionalReserva;
	private ReservaDTOInsert reservaDTOInsert;
	private UserDTO userDTO;
	private EstoqueDTO estoqueDTO;
	private User user;
	private Estoque estoque;
	private Optional<User> optionalUser;
	private Optional<Estoque> optionalEstoque;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startReserva();
	}

	@Test
	public void whenFindByIdThenReturnReservaDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalReserva);
		ReservaDTO response = service.findReservaById(ID);
		assertNotNull(response);
		assertEquals(ReservaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQtdReservada());
		assertEquals(DATA, response.getDataReserva());
		assertEquals(ATIVA, response.getStatusReserva());
	}

	@Test
	public void whenFindReservaByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findReservaById(ID));
	}

	@Test
	public void testCreate() {
		when(repository.save(any(Reserva.class))).thenReturn(reserva);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(estoqueRepository.findById(anyLong())).thenReturn(optionalEstoque);
		ReservaDTO result = service.create(reservaDTOInsert);
		assertEquals(ReservaDTO.class, result.getClass());
		verify(repository, times(1)).save(any(Reserva.class));
	}

	@Test
	public void testCreate_EstoqueNotFound() {
		Long idEstoque = 1L;
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(reservaDTOInsert));
	}

	@Test
	public void testCreate_UserNotFound() {
		Long idUser = 1L;
		when(userRepository.findById(idUser)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(reservaDTOInsert));
	}

	@Test
	public void testCreate_QuantidadeMaiorDoQueDisponivel() {
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(estoqueRepository.findById(anyLong())).thenReturn(optionalEstoque);
		reservaDTOInsert.setQtdReservada(10);
		Assertions.assertThrows(DataIntegratyViolationException.class, () -> {
			service.create(reservaDTOInsert);
		});
		verify(repository, never()).save(any(Reserva.class));
	}

	@Test
	public void testCancelarReserva() {
		Long id = ID;
		estoque.getReservas().add(reserva);
		reserva.setEstoque(estoque);
		when(repository.findById(id)).thenReturn(Optional.of(reserva));
		service.cancelarReserva(id);
		verify(repository, times(1)).findById(id);
		verify(repository, times(1)).delete(reserva);
		assertFalse(estoque.getReservas().contains(reserva));
	}

	private void startReserva() {
		reserva = new Reserva(ID, QUANTIDADE, DATA, null, null, ATIVA);
		optionalReserva = Optional.of(reserva);
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, StatusEstoque.DISPONIVEL, null, null, QUANTIDADE, QUANTIDADE,
				QUANTIDADE);
		userDTO = new UserDTO(ID, "oi", "oi", "oi", null);
		reservaDTOInsert = new ReservaDTOInsert(userDTO, estoqueDTO);
		user = new User(ID, "oi", "oi", "oi", "123");
		estoque = new Estoque(ID, QUANTIDADE, StatusEstoque.DISPONIVEL);
		optionalUser = Optional.of(user);
		optionalEstoque = Optional.of(estoque);
	}

}
