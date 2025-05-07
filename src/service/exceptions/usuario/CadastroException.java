package service.exceptions.usuario;

public class CadastroException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroException() {
		super("Register not working");
	}
	
	public CadastroException(String mensagem) {
		super(mensagem);
	}
}
