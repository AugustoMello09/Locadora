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

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOInfo;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.FilmePagedDTO;
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public FilmeDTO findById(Long id) {
		Optional<Filme> obj = repository.findById(id);
		Filme entity = obj.orElseThrow(() -> new ObjectNotFoundException("Filme não encontrado"));
		return new FilmeDTO(entity);
	}

	@Transactional(readOnly = true)
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public List<Filme> findAll(Long idCategoria) {
		categoriaService.findById(idCategoria);
		return repository.findAllByCategory(idCategoria);
	}
	
	@Transactional(readOnly = true)
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public Page<FilmePagedDTO> findPaged(Pageable pageable) {
		Page<Filme> list = repository.findAll(pageable);
		return list.map(x -> new FilmePagedDTO(x));
	}
	
	

	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<FilmeDTOInfo> findAllDrop() {
		List<Filme> list = repository.findAll();
		List<FilmeDTOInfo> listDto = list.stream().
				map(x -> new FilmeDTOInfo(x)).collect(Collectors.toList());
		return listDto;
	}

	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public FilmeDTO update(FilmeDTOUpdate fil, Long id) {
		Filme entity = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Filme não encontrado"));
		entity.setNome(fil.getNome());
		entity.setDescricao(fil.getDescricao());
		entity.setDiretor(fil.getDiretor());
		repository.save(entity);
		return new FilmeDTO(entity);
	}
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public FilmeDTO create(FilmeDTO fil) {
		Filme entity = new Filme();
		entity.setId(fil.getId());
		entity.setNome(fil.getNome());
		entity.setDescricao(fil.getDescricao());
		entity.setDiretor(fil.getDiretor());
		entity.setValorAluguel(fil.getValorAluguel());
		Long estoqueId = fil.getEstoque().getId();
		if (estoqueId != null) {
			Estoque estoque = estoqueRepository.findById(estoqueId)
					.orElseThrow(() -> new ObjectNotFoundException("Estoque não encontrado"));
			entity.setEstoque(estoque);
		}
		Long categoriaId = fil.getCategoria().getId();
		if (categoriaId != null) {
			Categoria categoria = categoriaRepository.findById(categoriaId)
					.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
			entity.setCategoria(categoria);
		}
		repository.save(entity);
		return new FilmeDTO(entity);	
	}

	

	

}
