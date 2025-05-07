package service.exceptions.usuario;

public class CadastroUsuarioJaExistenteException extends CadastroException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroUsuarioJaExistenteException() {
		super("Username is not available");
	}
	
	public CadastroUsuarioJaExistenteException(String mensagem) {
		super(mensagem);
	}
}
