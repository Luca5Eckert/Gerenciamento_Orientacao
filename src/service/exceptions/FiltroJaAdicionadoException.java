package service.exceptions;

public class FiltroJaAdicionadoException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroJaAdicionadoException() {
		super("Filtro já adicionado");
	}
	
	public FiltroJaAdicionadoException(String mensagem) {
		super(mensagem);
	}
}
