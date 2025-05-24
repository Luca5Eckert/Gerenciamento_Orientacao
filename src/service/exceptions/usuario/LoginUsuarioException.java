package service.exceptions.usuario;

public class LoginUsuarioException extends LoginException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginUsuarioException() {
		super("Login username incorrect");
	}
	
	public LoginUsuarioException(String mensagem) {
		super(mensagem);
	}
	
}
