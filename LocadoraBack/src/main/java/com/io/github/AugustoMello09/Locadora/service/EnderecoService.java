package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EnderecoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Endereco;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EnderecoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public EnderecoDTO findById(Long id) {
		Optional<Endereco> end = repository.findById(id);
		Endereco entity = end.orElseThrow(() -> new ObjectNotFoundException("Endereço Não encontrado"));
		return new EnderecoDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public EnderecoDTO create(EnderecoDTO objDto) {
		Endereco entity = new Endereco();
		entity.setId(objDto.getId());
		entity.setCep(objDto.getCep());
		entity.setBairro(objDto.getBairro());
		entity.setComplemento(objDto.getComplemento());
		entity.setLogradouro(objDto.getLogradouro());
		entity.setNumero(objDto.getNumero());
		Long cidadeId = objDto.getCidade().getId();
		if (cidadeId != null) {
			Cidade cidade = cidadeRepository.findById(cidadeId)
					.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada"));
			entity.setCidade(cidade);
		}
		Long userId = objDto.getUser().getId();
		if (userId != null) {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
			entity.setUser(user);
		}
		repository.save(entity);
		return new EnderecoDTO(entity);
	}

}
