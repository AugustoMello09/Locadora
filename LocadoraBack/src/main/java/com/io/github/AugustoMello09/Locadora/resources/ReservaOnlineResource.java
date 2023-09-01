package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTOInsert;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;
import com.io.github.AugustoMello09.Locadora.service.ReservaOnlineService;

@RestController
@RequestMapping(value = "/reservasOnline")
public class ReservaOnlineResource {

	@Autowired
	private ReservaOnlineService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ReservaOnlineDTO> findById(@PathVariable Long id) {
		ReservaOnlineDTO reser = service.findReservaOnlineById(id);
		return ResponseEntity.ok().body(reser);
	}
	
	@GetMapping
	public ResponseEntity<Page<ReservaOnlineDTO>> findAllPaged(Pageable pageable){
		Page<ReservaOnlineDTO> obj = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<List<ReservaOnlineDTO>> findAll(){
		List<ReservaOnline> list = service.findAll();
		List<ReservaOnlineDTO> listDto = list.stream().map(x -> new ReservaOnlineDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<ReservaOnlineDTO> create(@Valid @RequestBody ReservaOnlineDTOInsert objDto) {
		ReservaOnlineDTO newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PostMapping(value = "/cancelar/{id}")
	public ResponseEntity<Void> cancelar(@PathVariable Long id) {
		service.cancelarReserva(id);
		return ResponseEntity.ok().build();
	}

}
