package com.algaworks.socialbooks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutorRepository;
import com.algaworks.socialbooks.service.exception.AutorExistentesException;

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
}
