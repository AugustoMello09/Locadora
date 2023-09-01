package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.CategoriaDTO;
import com.io.github.AugustoMello09.Locadora.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){
		CategoriaDTO cat = service.findById(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> findAllPaged(Pageable pageable){
		Page<CategoriaDTO> cat = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(cat);
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<CategoriaDTO> listDto = service.findAll();
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO catDto){
		CategoriaDTO newDto = service.create(catDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@Valid @RequestBody CategoriaDTO catDto, @PathVariable Long id){
		CategoriaDTO obj = service.update(catDto, id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
