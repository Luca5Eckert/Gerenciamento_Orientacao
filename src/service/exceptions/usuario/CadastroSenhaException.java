package service.exceptions.usuario;

public class CadastroSenhaException extends CadastroException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroSenhaException() {
		super("Passworld Invalition");
	}
	
	public CadastroSenhaException(String mensagem) {
		super(mensagem);
	}
}
