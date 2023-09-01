package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.service.EstoqueService;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueResource {
	
	@Autowired
	private EstoqueService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstoqueDTO> findById(@PathVariable Long id){
		EstoqueDTO est = service.findEstoqueById(id);
		return ResponseEntity.ok().body(est);
	}
	
	@GetMapping
	public ResponseEntity<List<EstoqueDTO>> findAll(){
		List<EstoqueDTO> listDto = service.findAll();
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<EstoqueDTO> create(@RequestBody EstoqueDTO est){
		EstoqueDTO newDto = service.create(est);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EstoqueDTO> update(@RequestBody EstoqueDTO est, @PathVariable Long id){
		EstoqueDTO obj = service.update(est, id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
