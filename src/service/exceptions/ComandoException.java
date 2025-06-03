package service.exceptions;

public class ComandoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComandoException() {
		super();
	}

	public ComandoException(String mensagem) {
		super(mensagem);
	}

}
