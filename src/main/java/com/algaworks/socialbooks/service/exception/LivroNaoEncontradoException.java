package com.algaworks.socialbooks.service.exception;

public class LivroNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4529112685286480918L;
	
	public LivroNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	public LivroNaoEncontradoException (String mensagem , Throwable causa) {
		super(mensagem,causa);
	}
}
