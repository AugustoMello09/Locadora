package com.io.github.AugustoMello09.Locadora.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTOPList;
import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.entity.Locacao;
import com.io.github.AugustoMello09.Locadora.entity.Multa;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.LocacaoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public LocacaoDTO findById(Long id) {
		Optional<Locacao> locacao = repository.findById(id);
		Locacao entity = locacao.orElseThrow(() -> new ObjectNotFoundException("Locacao não encontrada"));
		return new LocacaoDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<LocacaoDTOPList> findAll() {
		List<Locacao> list = repository.findAll();
		List<LocacaoDTOPList> listDto = list.stream().map(x -> new LocacaoDTOPList(x)).collect(Collectors.toList());
		return listDto;
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public LocacaoDTO create(@Valid LocacaoDTO obj) {
		Locacao entity = new Locacao();
		entity.setId(obj.getId());
		entity.setQtd(obj.getQtd());
		entity.setformaPamento(obj.getFormaPagamento());
		entity.setDataLocacao(LocalDateTime.now());
		entity.setDataDevolucao(null);
		entity.setDataMaxDevolucao(entity.getDataLocacao().plusDays(7));
		Long userId = obj.getUser().getId();
		if (userId != null) {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
			entity.setUser(user);
		}
		Long filmeId = obj.getFilme().getId();
		if (filmeId != null) {	
			Filme filme = filmeRepository.findById(filmeId)
					.orElseThrow(() -> new ObjectNotFoundException("Filme não encontrado"));
			entity.setFilme(filme);
		}
		Estoque estoque = entity.getFilme().getEstoque();
		if (entity.getQtd() > estoque.getQuantidade()) {
			throw new DataIntegratyViolationException("Quantidade solicitada maior do que a disponível no estoque");
		}
		estoque.setQuantidade(estoque.getQuantidade() - entity.getQtd());
		repository.save(entity);
		return new LocacaoDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void devolver(Long idLocacao, LocacaoDTO dto) {
		Locacao entity = repository.findById(idLocacao)
				.orElseThrow(() -> new ObjectNotFoundException("Locacao não encontrada"));
		LocalDateTime dataDevolucao = LocalDateTime.now().plusDays(8);
		entity.setDataDevolucao(dataDevolucao);
		LocalDateTime dataMaxDevolucao = entity.getDataMaxDevolucao();
		if (dataMaxDevolucao != null && dataDevolucao.isAfter(dataMaxDevolucao)) {
			Double multaValor = 100.0;
			Multa multa = new Multa();
			multa.setValor(multaValor);
			multa.setformaPamento(EstadoPagamento.PENDENTE);
			multa.setLocacao(entity);
			entity.getMultas().add(multa);
		}
		int quantidadeDevolvida = dto.getQtd();
		if (quantidadeDevolvida <= entity.getQtd()) {
			Estoque estoque = entity.getFilme().getEstoque();
			estoque.setQtd(estoque.getQuantidade() + quantidadeDevolvida);
			estoqueRepository.save(estoque);
		} else {
		  throw new DataIntegratyViolationException("Quantidade escolhida maior do que a alugada, verifique a quantia alugada.");
		}
		repository.save(entity);
	}

}
