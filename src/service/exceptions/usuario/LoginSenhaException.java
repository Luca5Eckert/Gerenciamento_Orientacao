package service.exceptions.usuario;

public class LoginSenhaException extends LoginException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginSenhaException() {
		super("Login password incorrect");
	}
	
	public LoginSenhaException(String mensagem) {
		super(mensagem);
	}
	
}
