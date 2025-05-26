package service.exceptions.usuario;

public class EmailInvalidoException extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailInvalidoException() {
		super("Email invalido");
	}
	
	public EmailInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	
	
}
