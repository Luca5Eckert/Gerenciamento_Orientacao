package service.exceptions.usuario;

public class LoginUsuarioException extends LoginException {
	
	public LoginUsuarioException() {
		super("Login username incorrect");
	}
	
	public LoginUsuarioException(String mensagem) {
		super(mensagem);
	}
	
}
