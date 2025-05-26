package service.exceptions.usuario;

public class LoginException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super("Login incorrect");
	}
	
	public LoginException(String mensagem) {
		super(mensagem);
	}
	
}
