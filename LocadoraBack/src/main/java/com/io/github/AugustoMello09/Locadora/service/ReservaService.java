package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTOInsert;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Reserva;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public ReservaDTO findReservaById(Long id) {
		Optional<Reserva> obj = repository.findById(id);
		Reserva entity = obj.orElseThrow(() -> new ObjectNotFoundException("Reserva não encontrada"));
		return new ReservaDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void cancelarReserva(Long id) {
		Reserva reserva = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Reserva não encontrada"));
		Estoque estoque = reserva.getEstoque();
		estoque.getReservas().remove(reserva);
		repository.delete(reserva);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public ReservaDTO create(ReservaDTOInsert objDto) {
		Reserva entity = new Reserva();
		Long userId = objDto.getUser().getId();
		if (userId != null) {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
			entity.setUser(user);
		}
		entity.setId(objDto.getId());
		entity.setQtdReservada(objDto.getQtdReservada());
		entity.setDataReserva(objDto.getDataReserva());
		entity.setStatus(StatusReserva.ATIVA);
		Long estoqueId = objDto.getEstoque().getId();
		if (estoqueId != null) {
			Estoque estoque = estoqueRepository.findById(estoqueId)
					.orElseThrow(() -> new ObjectNotFoundException("Estoque não encontrado"));
			if (entity.getQtdReservada() > estoque.getQuantidade()) {
				throw new DataIntegratyViolationException("Quantidade solicitada maior do que a disponível no estoque");
			}
			entity.setEstoque(estoque);
			repository.save(entity);
		}
		return new ReservaDTO(entity);
	}

}
