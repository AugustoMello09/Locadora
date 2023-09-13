package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Long id){
		EstadoDTO endDto = service.findById(id);
		return ResponseEntity.ok().body(endDto);
	}
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<EstadoDTO> listDto = service.findAll();
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<EstadoDTO> create(@Valid @RequestBody EstadoDTO estDto){
		EstadoDTO newDto = service.create(estDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
