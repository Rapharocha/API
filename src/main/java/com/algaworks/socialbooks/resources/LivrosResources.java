package com.algaworks.socialbooks.resources;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.service.LivrosService;
import com.algaworks.socialbooks.service.exception.LivroNaoEncontradoException;

import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosService livrosService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Livro>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(name = "corpo", value="Salvando um novo livro") @Validated @RequestBody Livro livro) {
		livro = livrosService.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@ApiParam(value = "ID de um livro", example = "1") @PathVariable("id") Long id) {
		
		Optional<Livro> livro = livrosService.buscar(id);
	    return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@ApiParam(value = "ID de um livro", example = "1") @PathVariable("id") Long id) {
	
			livrosService.deletar(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar (@ApiParam(name = "corpo", value="Atualizando um livro") @RequestBody Livro livro ,@ApiParam(value = "ID de um livro", example = "1") @PathVariable("id") Long id) {
		
		livro.setId(id);
		livrosService.atualizar(livro);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@ApiParam(value = "ID de um livro", example = "1") @PathVariable("id") Long livroId , @ApiParam(name = "corpo", value="Adicionando um Comentário")@RequestBody Comentario comentario) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comentario.setUsuario(auth.getName());
		livrosService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentario(@ApiParam(value = "ID de um livro", example = "1") @PathVariable("id") Long livroId){
		
		List<Comentario>comentarios = livrosService.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
}
