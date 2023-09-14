package com.io.github.AugustoMello09.Locadora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTOInsert;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaOnlineRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class ReservaOnlineService {

	@Autowired
	private ReservaOnlineRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private AuthService authService;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')or @authService.authenticated().getId() == #id")
	public ReservaOnlineDTO findReservaOnlineById(Long id) {
		Optional<ReservaOnline> obj = repository.findById(id);
		ReservaOnline entity = obj.orElseThrow(() -> new ObjectNotFoundException("ReservaOnline não encontrada"));
		return new ReservaOnlineDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<ReservaOnlineDTO> findAll() {
		List<ReservaOnline> lista = repository.findAll();
		List<ReservaOnlineDTO> listDto = lista.stream().map(ReservaOnlineDTO::new).collect(Collectors.toList());
		return listDto;
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public Page<ReservaOnlineDTO> findAllPaged(Pageable pageable) {
		Long authenticatedUserId = authService.authenticated().getId();
		Page<ReservaOnline> list = repository.findByUser_Id(authenticatedUserId, pageable);
		return list.map(x -> new ReservaOnlineDTO(x));
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void cancelarReserva(Long id) {
		ReservaOnline reserva = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ReservaOnline não encontrada"));
		Estoque estoque = reserva.getEstoque();
		estoque.getReservasOnline().remove(reserva);
		repository.delete(reserva);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public ReservaOnlineDTO create(ReservaOnlineDTOInsert objDto) {
		ReservaOnline entity = new ReservaOnline();
		entity.setId(objDto.getId());
		entity.setQtdReservada(objDto.getQtdReservada());
		entity.setDataReserva(objDto.getDataReserva());
		entity.setStatus(StatusReserva.ATIVA);
		Long userId = objDto.getUser().getId();
		if (userId != null) {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
			entity.setUser(user);
		}
		EstoqueDTO estoqueDTO = objDto.getEstoque();
		Long estoqueId = estoqueDTO.getId();
		if (estoqueId != null) {
			Estoque estoque = estoqueRepository.findById(estoqueId)
					.orElseThrow(() -> new ObjectNotFoundException("Estoque não encontrado"));
			if (entity.getQtdReservada() > estoque.getQuantidade()) {
				throw new DataIntegratyViolationException("Quantidade solicitada maior do que a disponível no estoque");
			}
			entity.setEstoque(estoque);
			repository.save(entity);
		}
		return new ReservaOnlineDTO(entity);
	}

	

}
