package br.org.mcord.famec.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -404137443498195002L;
	
	public NotFoundException(String message) {
		super(message);
	}

}
