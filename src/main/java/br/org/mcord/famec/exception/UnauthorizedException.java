package br.org.mcord.famec.exception;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 7165730372671440559L;
	
	public UnauthorizedException(String message) {
		super(message);
	}

}
