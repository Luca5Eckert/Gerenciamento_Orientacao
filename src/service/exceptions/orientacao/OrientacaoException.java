package service.exceptions.orientacao;

public class OrientacaoException extends Exception{

	public OrientacaoException() {
		super("Action in orientation");
	}
	
	public OrientacaoException(String mensagem) {
		super(mensagem);
	}
	
}
