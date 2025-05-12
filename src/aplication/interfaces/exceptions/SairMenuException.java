package aplication.interfaces.exceptions;

public class SairMenuException extends Exception{

	public SairMenuException(String mensagem) {
		super(mensagem);
	}
	
	public SairMenuException() {
		super(" Saindo do menu ");
	}
	
}
