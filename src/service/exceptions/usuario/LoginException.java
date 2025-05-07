package service.exceptions.usuario;

public class LoginException extends Exception {
	
	public LoginException() {
		super("Login incorrect");
	}
	
	public LoginException(String mensagem) {
		super(mensagem);
	}
	
}
