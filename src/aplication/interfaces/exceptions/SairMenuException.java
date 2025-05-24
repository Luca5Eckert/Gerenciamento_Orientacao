package aplication.interfaces.exceptions;

public class SairMenuException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SairMenuException(String mensagem) {
		super(mensagem);
	}
	
	public SairMenuException() {
		super(" Saindo do menu ");
	}
	
}
