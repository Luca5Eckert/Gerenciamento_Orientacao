package service.exceptions.orientacao;

public class OrientacaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrientacaoException() {
		super("Action in orientation");
	}
	
	public OrientacaoException(String mensagem) {
		super(mensagem);
	}
	
}
