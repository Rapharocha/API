package com.algaworks.socialbooks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutorRepository;
import com.algaworks.socialbooks.service.exception.AutorExistentesException;
import com.algaworks.socialbooks.service.exception.AutorNaoEncontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> listar(){
		return autorRepository.findAll();
		
	}
	
	public Autor salvar(Autor autor) {
		System.out.println();
		
		if(autor.getId() != null) {
			Optional<Autor> a = autorRepository.findById(autor.getId());
			if(a.isPresent()) {
				throw new AutorExistentesException("O autor já existe");
			}
		}
		return autorRepository.save(autor);
	}
	
	public Optional<Autor> buscar(Long id) {
		
		Optional<Autor> autor = autorRepository.findById(id);
		
		if(autor.isEmpty()) {
			throw new AutorNaoEncontradoException("O autor não pôde ser encontrado");
		}
		
		return autor;
	}
}
