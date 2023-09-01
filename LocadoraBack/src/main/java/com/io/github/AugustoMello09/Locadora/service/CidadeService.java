package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public CidadeDTO findById(Long id) {
		Optional<Cidade> obj = repository.findById(id);
		Cidade entity = obj.orElseThrow(() -> new ObjectNotFoundException("Cidade Não encontrada"));
		return new CidadeDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public CidadeDTO create(CidadeDTO cidDto) {
		Cidade cidade = new Cidade();
		cidade.setId(cidDto.getId());
		cidade.setName(cidDto.getName());
		Long estadoId = cidDto.getEstadoId().getId();
		if (estadoId != null) {
			Estado estado = estadoRepository.findById(estadoId)
					.orElseThrow(() -> new ObjectNotFoundException("Estado Não encontrado"));
			cidade.setEstado(estado);
		}
		repository.save(cidade);
		return new CidadeDTO(cidade);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar cidades associadas a estado");
		}
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public CidadeDTO findByNome(String name) {
	  Cidade cid = repository.findByName(name);
	  if (cid == null) {
	        throw new ObjectNotFoundException("Cidade não encontrada");
	  }
	  return new CidadeDTO(cid);
	}

	
}
