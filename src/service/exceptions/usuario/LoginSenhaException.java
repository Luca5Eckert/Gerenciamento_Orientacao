package service.exceptions.usuario;

public class LoginSenhaException extends LoginException {
	
	public LoginSenhaException() {
		super("Login password incorrect");
	}
	
	public LoginSenhaException(String mensagem) {
		super(mensagem);
	}
	
}
