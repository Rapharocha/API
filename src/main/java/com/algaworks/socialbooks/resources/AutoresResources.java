package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.service.AutoresService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/autores")
public class AutoresResources {

	
	@Autowired
	private AutoresService autoresService;
	
	@ApiOperation(value = "Listar autores API")
	@GetMapping
	public ResponseEntity<List<Autor>>listar(){
		
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}
	
	@ApiOperation(value = "Salvar autores API")
    @PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(name = "corpo", value="Salvando um novo autor") @Validated @RequestBody Autor autor){
		autor = autoresService.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@ApiOperation(value = "Buscar autores API")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Autor>> buscar(@ApiParam(value = "ID de um autor", example = "1") @PathVariable("id") Long id){
    	return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(id)); 	
    }
}
