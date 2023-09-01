package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTOPList;
import com.io.github.AugustoMello09.Locadora.service.LocacaoService;

@RestController
@RequestMapping(value = "locacao")
public class LocacaoResource {

	@Autowired
	private LocacaoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<LocacaoDTO> findByIdDto(@PathVariable Long id) {
		LocacaoDTO locacao = service.findById(id);
		return ResponseEntity.ok().body(locacao);
	}
	
	@GetMapping
	public ResponseEntity<List<LocacaoDTOPList>> findAll(){
		List<LocacaoDTOPList> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@PostMapping
	public ResponseEntity<LocacaoDTO> create(@Valid @RequestBody LocacaoDTO obj){
        LocacaoDTO newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newObj);
    }
	
	@PostMapping(value = "devolver/{idLocacao}")
	public ResponseEntity<Void> devolver(@PathVariable Long idLocacao,
			@Valid @RequestBody LocacaoDTO dto){
		service.devolver(idLocacao, dto);
		return ResponseEntity.ok().build();
	}	
}
