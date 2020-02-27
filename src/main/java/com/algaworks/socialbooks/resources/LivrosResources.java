package com.algaworks.socialbooks.resources;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.service.LivrosService;
import com.algaworks.socialbooks.service.exception.LivroNaoEncontradoException;



@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosService livrosService;
	
	@GetMapping
	public ResponseEntity<List<Livro>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		
		 
		Optional<Livro> livro = null;
		try {
		livro = livrosService.buscar(id);
			
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
	    return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		try {
			livrosService.deletar(id);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar (@RequestBody Livro livro , @PathVariable("id") Long id) {
		
		livro.setId(id);
		try {
			livrosService.atualizar(livro);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
