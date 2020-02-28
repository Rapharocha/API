package com.algaworks.socialbooks.service.exception;

public class AutorExistentesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7068455037510589117L;
	public AutorExistentesException (String mensagem) {
		super(mensagem);
	}
	public AutorExistentesException (String mensagem , Throwable causa) {
		super(mensagem,causa);
	}
}
