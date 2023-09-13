package com.io.github.AugustoMello09.Locadora.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	@Autowired
	private FilmeRepository filmeRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public EstoqueDTO findEstoqueById(Long id) {
		Optional<Estoque> obj = repository.findById(id);
		Estoque entity = obj.orElseThrow(() -> new ObjectNotFoundException("Estoque Não encontrado"));
		return new EstoqueDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<EstoqueDTO> findAll() {
		List<Estoque> list = repository.findAll();
		List<EstoqueDTO> listDto = list.stream().map(EstoqueDTO::new).collect(Collectors.toList());
		return listDto;
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public EstoqueDTO create(EstoqueDTO est) {
		Estoque entity = new Estoque();
		entity.setId(est.getId());
		entity.setQuantidade(est.getQuantidade());
		entity.setStatus(est.getStatus());
		repository.save(entity);
		return new EstoqueDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public EstoqueDTO update(EstoqueDTO est, Long id) {
		Estoque entity = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Estoque Não encontrado"));
		Optional<Filme> filme = filmeRepository.findByEstoque(entity);
		if (filme.isPresent()) {
			throw new DataIntegratyViolationException(
					"Este estoque está sendo utilizado por um filme e não pode ser atualizado se não for por alguma operação que altere o valor como DEVOLUÇÂO e LOCAÇÃO");
		}
		entity.setQuantidade(est.getQuantidade());
		repository.save(entity);
		entity.setQtd(entity.getQuantidade());
		return new EstoqueDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void delete(Long id) {
	    findEstoqueById(id);
	    try {
	        repository.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
	        throw new DataIntegrityViolationException("Não é possível excluir o estoque porque está associado a filmes.");
	    }
	}

	

}
